package com.example.myrecipes_lucas_lisa_nguyen_chandler.model

data class CategoryResponse(val categories: List<Category>)

data class Category(
    val idCategory: String,
    val strCategory: String,
    val strCategoryThumb: String,
    val strCategoryDescription: String
)