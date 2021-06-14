package com.example.recipeapp.api

import com.example.recipeapp.models.RecipeResponse
import com.example.recipeapp.utils.Constants.END_POINT
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET(END_POINT)
    suspend fun getRecipe(): Response<RecipeResponse>
}