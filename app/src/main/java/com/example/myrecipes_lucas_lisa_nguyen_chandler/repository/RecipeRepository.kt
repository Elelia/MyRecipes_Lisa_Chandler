package com.example.myrecipes_lucas_lisa_nguyen_chandler.repository

import com.example.myrecipes_lucas_lisa_nguyen_chandler.RetrofitInstance

class RecipeRepository {
    private val api = RetrofitInstance.api

    suspend fun getCategories() = api.getCategories()
}