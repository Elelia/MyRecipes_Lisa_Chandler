package com.example.myrecipes_lucas_lisa_nguyen_chandler.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myrecipes_lucas_lisa_nguyen_chandler.R
import com.example.myrecipes_lucas_lisa_nguyen_chandler.model.Recipe

class RecipeListAdapter(
    private val onItemClick: (Recipe) -> Unit
) : RecyclerView.Adapter<RecipeListAdapter.RecipeViewHolder>() {

    private var recipes: List<Recipe> = emptyList()

    fun submitList(list: List<Recipe>) {
        recipes = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recipe, parent, false)
        return RecipeViewHolder(view)
    }

    override fun getItemCount(): Int = recipes.size

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.bind(recipes[position])
        val recipe = recipes[position]
        holder.bind(recipe)

        holder.itemView.setOnClickListener {
            onItemClick(recipe)
        }
    }

    class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.imageRecipe)
        private val textView: TextView = itemView.findViewById(R.id.textRecipe)

        fun bind(recipe: Recipe) {
            textView.text = recipe.strMeal
            Glide.with(itemView.context)
                .load(recipe.strMealThumb)
                .into(imageView)
        }
    }
}