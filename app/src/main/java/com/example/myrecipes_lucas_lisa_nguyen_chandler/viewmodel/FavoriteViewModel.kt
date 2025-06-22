package com.example.myrecipes_lucas_lisa_nguyen_chandler.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.myrecipes_lucas_lisa_nguyen_chandler.AppDatabase
import com.example.myrecipes_lucas_lisa_nguyen_chandler.Entity.FavoriteRecipe
import kotlinx.coroutines.launch

class FavoriteViewModel(application: Application) : AndroidViewModel(application) {
    private val dao = AppDatabase.getDatabase(application).favoriteRecipeDao()
    private val _favorites = MutableLiveData<List<FavoriteRecipe>>()
    val favorites: LiveData<List<FavoriteRecipe>> = _favorites

    fun loadFavorites() {
        viewModelScope.launch {
            _favorites.postValue(dao.getAllFavorites())
        }
    }

    fun deleteFavorite(recipe: FavoriteRecipe) {
        viewModelScope.launch {
            dao.deleteFavorite(recipe)
            loadFavorites()
        }
    }
}
