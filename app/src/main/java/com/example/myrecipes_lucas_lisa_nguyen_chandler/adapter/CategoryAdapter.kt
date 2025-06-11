package com.example.myrecipes_lucas_lisa_nguyen_chandler.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myrecipes_lucas_lisa_nguyen_chandler.R
import com.example.myrecipes_lucas_lisa_nguyen_chandler.model.Category

class CategoryAdapter : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    private var categories: List<Category> = emptyList()

    fun submitList(list: List<Category>) {
        categories = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_category, parent, false)
        return CategoryViewHolder(view)
    }

    override fun getItemCount(): Int = categories.size

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(categories[position])
    }

    class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.imageCategory)
        private val textView: TextView = itemView.findViewById(R.id.textCategory)

        fun bind(category: Category) {
            textView.text = category.strCategory
            Glide.with(itemView.context)
                .load(category.strCategoryThumb)
                .into(imageView)
        }
    }
}