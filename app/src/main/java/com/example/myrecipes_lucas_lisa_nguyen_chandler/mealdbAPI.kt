package com.example.myrecipes_lucas_lisa_nguyen_chandler

import com.example.myrecipes_lucas_lisa_nguyen_chandler.model.CategoryResponse
import com.example.myrecipes_lucas_lisa_nguyen_chandler.model.RecipeListResponse
import com.example.myrecipes_lucas_lisa_nguyen_chandler.model.RecipeDetailResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface mealdbAPI {
    @GET("categories.php")
    suspend fun getCategories(): CategoryResponse

    @GET("filter.php")
    suspend fun getMealsByCategory(@Query("c") category: String): RecipeListResponse

    @GET("lookup.php")
    suspend fun getMealDetails(@Query("i") id: String): RecipeDetailResponse
}