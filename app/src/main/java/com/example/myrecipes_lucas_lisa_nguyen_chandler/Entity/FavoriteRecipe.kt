package com.example.myrecipes_lucas_lisa_nguyen_chandler.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_recipes")
data class FavoriteRecipe(
    @PrimaryKey val idMeal: String,
    val strMeal: String,
    val strMealThumb: String,
    val savedAt: Long // timestamp
)