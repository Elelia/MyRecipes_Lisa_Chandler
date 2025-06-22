package com.example.myrecipes_lucas_lisa_nguyen_chandler.model

data class RecipeListResponse(
    val meals: List<Recipe>
)

data class Recipe(
    val idMeal: String,
    val strMeal: String,
    val strMealThumb: String
)

