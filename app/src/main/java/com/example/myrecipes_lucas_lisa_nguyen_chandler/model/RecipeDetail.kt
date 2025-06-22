package com.example.myrecipes_lucas_lisa_nguyen_chandler.model

data class RecipeDetailResponse(val meals: List<RecipeDetail>)

data class RecipeDetail (
    val idMeal: String,
    val strMeal: String,
    val strCategory: String?,
    val strArea: String?,
    val strInstructions: String?,
    val strMealThumb: String?,
    val strIngredient1: String?, val strMeasure1: String?,
    val strIngredient2: String?, val strMeasure2: String?,
    // ... jusqu’à 20 (car l’API a 20 ingrédients max)
)