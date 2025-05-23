package com.example.myrecipes_lucas_lisa_nguyen_chandler.model

data class RecipeListResponse(val meals: List<RecipeSummary>)

data class RecipeSummary (
    val idMeal: String,
    val strMeal: String,
    val strMealThumb: String
)