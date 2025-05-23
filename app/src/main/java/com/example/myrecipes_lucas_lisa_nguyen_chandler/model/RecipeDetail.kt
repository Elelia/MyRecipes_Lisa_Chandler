package com.example.myrecipes_lucas_lisa_nguyen_chandler.model

data class RecipeDetailResponse(val meals: List<RecipeDetail>)

data class RecipeDetail (
    val idMeal: String,
    val strMeal: String,
    val strCategory: String,
    val strInstructions: String,
    val strMealThumb: String,
    val strYoutube: String?
)