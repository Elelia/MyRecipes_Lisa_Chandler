package com.example.myrecipes_lucas_lisa_nguyen_chandler.adapter

import android.icu.text.SimpleDateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myrecipes_lucas_lisa_nguyen_chandler.Entity.FavoriteRecipe
import com.example.myrecipes_lucas_lisa_nguyen_chandler.R
import java.util.Date
import java.util.Locale

class FavoriteAdapter(
    private val onDeleteClick: (FavoriteRecipe) -> Unit
) : RecyclerView.Adapter<FavoriteAdapter.FavViewHolder>() {

    private var items = listOf<FavoriteRecipe>()

    fun submitList(list: List<FavoriteRecipe>) {
        items = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_favorite, parent, false)
        return FavViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavViewHolder, position: Int) {
        holder.bind(items[position])
        holder.itemView.findViewById<ImageButton>(R.id.buttonDelete).setOnClickListener {
            onDeleteClick(items[position])
        }
    }

    override fun getItemCount(): Int = items.size

    class FavViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val image = view.findViewById<ImageView>(R.id.imageFavorite)
        private val name = view.findViewById<TextView>(R.id.textName)
        private val date = view.findViewById<TextView>(R.id.textDate)

        fun bind(recipe: FavoriteRecipe) {
            name.text = recipe.strMeal
            val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
            date.text = sdf.format(Date(recipe.savedAt))
            Glide.with(itemView.context).load(recipe.strMealThumb).into(image)
        }
    }
}
