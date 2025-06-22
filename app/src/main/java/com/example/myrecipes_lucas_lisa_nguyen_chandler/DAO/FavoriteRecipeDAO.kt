package com.example.myrecipes_lucas_lisa_nguyen_chandler.DAO

import androidx.room.*
import com.example.myrecipes_lucas_lisa_nguyen_chandler.Entity.FavoriteRecipe

@Dao
interface FavoriteRecipeDao {
    @Query("SELECT * FROM favorite_recipes ORDER BY savedAt DESC")
    suspend fun getAllFavorites(): List<FavoriteRecipe>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(recipe: FavoriteRecipe)

    @Delete
    suspend fun deleteFavorite(recipe: FavoriteRecipe)
}
