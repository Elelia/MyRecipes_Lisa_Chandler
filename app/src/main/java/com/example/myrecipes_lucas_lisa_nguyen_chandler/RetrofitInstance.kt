package com.example.myrecipes_lucas_lisa_nguyen_chandler

import com.example.myrecipes_lucas_lisa_nguyen_chandler.adapter.RecipeDetailAdapter
import com.example.myrecipes_lucas_lisa_nguyen_chandler.model.RecipeDetail
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//permet d'avoir un singleton de l'instance
//évite d'initialiser à chaque appel
object RetrofitInstance {
    private const val BASE_URL = "https://www.themealdb.com/api/json/v1/1/"

    private val gson = GsonBuilder()
        .registerTypeAdapter(RecipeDetail::class.java, RecipeDetailAdapter())
        .create()

    val api: mealdbAPI by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(mealdbAPI::class.java)
    }
}