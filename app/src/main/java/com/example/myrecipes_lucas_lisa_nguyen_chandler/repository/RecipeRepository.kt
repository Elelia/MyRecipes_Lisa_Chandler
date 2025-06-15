package com.example.myrecipes_lucas_lisa_nguyen_chandler.repository

import android.util.Log
import com.example.myrecipes_lucas_lisa_nguyen_chandler.RetrofitInstance
import com.example.myrecipes_lucas_lisa_nguyen_chandler.model.CategoryResponse

class RecipeRepository {
    private val api = RetrofitInstance.api

    //suspend fun getCategories() = api.getCategories()
    suspend fun getCategories(): CategoryResponse {
        Log.d("DEBUG", "→ Appel Retrofit en cours")
        return api.getCategories()
    }
}