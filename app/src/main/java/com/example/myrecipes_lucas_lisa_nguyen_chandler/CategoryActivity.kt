package com.example.myrecipes_lucas_lisa_nguyen_chandler

import android.content.Intent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myrecipes_lucas_lisa_nguyen_chandler.adapter.CategoryAdapter
import com.example.myrecipes_lucas_lisa_nguyen_chandler.viewmodel.CategoryViewModel
import com.google.android.material.navigation.NavigationView

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

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)
        val navView = findViewById<NavigationView>(R.id.navigation_view)

        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar,
            R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_categories -> {
                    // Tu es déjà sur CategoryActivity, optionnel : rafraîchir
                    true
                }
                R.id.nav_favorites -> {
                    val intent = Intent(this, FavoriteActivity::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }.also {
                drawerLayout.closeDrawers()
            }
        }
    }
}