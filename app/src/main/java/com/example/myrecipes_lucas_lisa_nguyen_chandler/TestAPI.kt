package com.example.myrecipes_lucas_lisa_nguyen_chandler

import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    try {
        val categories = RetrofitInstance.api.getCategories()
        println("Catégories reçues :")
        categories.categories.forEach {
            println("- ${it.strCategory}")
        }
    } catch (e: Exception) {
        println("Erreur lors de l'appel API : ${e.message}")
    }
}
