package com.example.myrecipes_lucas_lisa_nguyen_chandler

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myrecipes_lucas_lisa_nguyen_chandler.adapter.FavoriteAdapter
import com.example.myrecipes_lucas_lisa_nguyen_chandler.viewmodel.FavoriteViewModel

class FavoriteActivity : AppCompatActivity() {

    private val viewModel: FavoriteViewModel by viewModels()
    private lateinit var adapter: FavoriteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewFavorites)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = FavoriteAdapter { recipe ->
            viewModel.deleteFavorite(recipe)
        }
        recyclerView.adapter = adapter

        viewModel.favorites.observe(this) {
            adapter.submitList(it)
        }

        viewModel.loadFavorites()
    }
}
