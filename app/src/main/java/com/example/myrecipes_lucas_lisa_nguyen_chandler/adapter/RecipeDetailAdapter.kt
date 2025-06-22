package com.example.myrecipes_lucas_lisa_nguyen_chandler.adapter

import com.example.myrecipes_lucas_lisa_nguyen_chandler.model.RecipeDetail
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class RecipeDetailAdapter : JsonDeserializer<RecipeDetail> {
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): RecipeDetail {
        val obj = json.asJsonObject
        val idMeal = obj["idMeal"].asString
        val strMeal = obj["strMeal"].asString
        val strCategory = obj["strCategory"]?.asString
        val strArea = obj["strArea"]?.asString
        val strInstructions = obj["strInstructions"]?.asString
        val strMealThumb = obj["strMealThumb"]?.asString

        val ingredients = mutableListOf<Pair<String, String>>()
        for (i in 1..20) {
            val ingredient = obj["strIngredient$i"]?.asString
            val measure = obj["strMeasure$i"]?.asString
            if (!ingredient.isNullOrBlank()) {
                ingredients.add(ingredient to (measure ?: ""))
            }
        }

        return RecipeDetail(
            idMeal, strMeal, strCategory, strArea, strInstructions, strMealThumb, ingredients
        )
    }
}