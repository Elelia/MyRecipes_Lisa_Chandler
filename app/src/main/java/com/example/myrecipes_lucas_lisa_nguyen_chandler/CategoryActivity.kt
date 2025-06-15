package com.example.myrecipes_lucas_lisa_nguyen_chandler

import android.content.Intent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myrecipes_lucas_lisa_nguyen_chandler.adapter.CategoryAdapter
import com.example.myrecipes_lucas_lisa_nguyen_chandler.viewmodel.CategoryViewModel

class CategoryActivity : AppCompatActivity() {

    private val viewModel: CategoryViewModel by viewModels()
    private lateinit var categoryAdapter: CategoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewCategories)
        recyclerView.layoutManager = LinearLayoutManager(this)
        categoryAdapter = CategoryAdapter { category ->
            val intent = Intent(this, RecipeListActivity::class.java)
            intent.putExtra("category_name", category.strCategory)
            startActivity(intent)
        }
        recyclerView.adapter = categoryAdapter

        viewModel.categories.observe(this) { categories ->
            categoryAdapter.submitList(categories)
        }

        Log.d("DEBUG", "→ Appel à viewModel.loadCategories()")
        viewModel.loadCategories()
    }
}