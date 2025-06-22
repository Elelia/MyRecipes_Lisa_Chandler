package com.example.myrecipes_lucas_lisa_nguyen_chandler
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myrecipes_lucas_lisa_nguyen_chandler.RetrofitInstance
import com.example.myrecipes_lucas_lisa_nguyen_chandler.adapter.RecipeListAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RecipeListActivity : BaseActivity() {

    private lateinit var recipeAdapter: RecipeListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_list)

        val categoryName = intent.getStringExtra("category_name") ?: ""

        title = "$categoryName Recipes"

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewRecipes)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recipeAdapter = RecipeListAdapter { recipe ->
            val intent = Intent(this, RecipeDetailActivity::class.java)
            intent.putExtra("meal_id", recipe.idMeal)
            startActivity(intent)
        }
        recyclerView.adapter = recipeAdapter

        // Charger les recettes
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val response = RetrofitInstance.api.getMealsByCategory(categoryName)
                recipeAdapter.submitList(response.meals)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
