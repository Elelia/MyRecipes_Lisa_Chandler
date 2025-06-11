package com.example.myrecipes_lucas_lisa_nguyen_chandler.viewmodel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myrecipes_lucas_lisa_nguyen_chandler.model.Category
import com.example.myrecipes_lucas_lisa_nguyen_chandler.repository.RecipeRepository
import kotlinx.coroutines.launch

class CategoryViewModel : ViewModel() {

    private val repository = RecipeRepository()

    private val _categories = MutableLiveData<List<Category>>()
    val categories: LiveData<List<Category>> get() = _categories

    fun loadCategories() {
        viewModelScope.launch {
            try {
                val response = repository.getCategories()
                _categories.postValue(response.categories)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}