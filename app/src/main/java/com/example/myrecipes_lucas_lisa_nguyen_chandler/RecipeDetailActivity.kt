package com.example.myrecipes_lucas_lisa_nguyen_chandler

import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.myrecipes_lucas_lisa_nguyen_chandler.Entity.FavoriteRecipe
import com.example.myrecipes_lucas_lisa_nguyen_chandler.model.RecipeDetail
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RecipeDetailActivity : AppCompatActivity() {
    private lateinit var currentMeal: RecipeDetail

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_detail)

        val mealId = intent.getStringExtra("meal_id") ?: return
        val buttonFavorite = findViewById<ImageButton>(R.id.buttonFavorite)

        CoroutineScope(Dispatchers.Main).launch {
            try {
                val response = RetrofitInstance.api.getMealDetails(mealId)
                val meal = response.meals.firstOrNull() ?: return@launch
                currentMeal = meal
                afficherDetails(meal)

                buttonFavorite.setOnClickListener {
                    val favorite = FavoriteRecipe(
                        idMeal = currentMeal.idMeal,
                        strMeal = currentMeal.strMeal,
                        strMealThumb = currentMeal.strMealThumb ?: "",
                        savedAt = System.currentTimeMillis()
                    )

                    CoroutineScope(Dispatchers.IO).launch {
                        AppDatabase.getDatabase(this@RecipeDetailActivity)
                            .favoriteRecipeDao()
                            .insertFavorite(favorite)

                        withContext(Dispatchers.Main) {
                            Toast.makeText(this@RecipeDetailActivity, "Ajouté aux favoris", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            } catch (e: Exception) {
                Log.e("DEBUG", "Erreur API détail recette : ${e.message}")
            }
        }
    }

    private fun afficherDetails(meal: RecipeDetail) {
        // Exemple
        findViewById<TextView>(R.id.textTitle).text = meal.strMeal
        findViewById<TextView>(R.id.textCategory).text = meal.strCategory
        findViewById<TextView>(R.id.textArea).text = meal.strArea
        findViewById<TextView>(R.id.textInstructions).text = meal.strInstructions
        Glide.with(this).load(meal.strMealThumb).into(findViewById(R.id.imageRecipe))

        // Ingredients + mesures dynamiques
        val ingredients = mutableListOf<String>()
        for (i in 1..20) {
            val ingredient = RecipeDetail::class.java.getDeclaredField("strIngredient$i").get(meal) as? String
            val measure = RecipeDetail::class.java.getDeclaredField("strMeasure$i").get(meal) as? String
            if (!ingredient.isNullOrBlank()) {
                ingredients.add("• $ingredient : $measure")
            }
        }
        findViewById<TextView>(R.id.textIngredients).text = ingredients.joinToString("\n")
    }
}
