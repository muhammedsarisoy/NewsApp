package com.example.newsappproject.ui.data.network

import androidx.collection.scatterSetOf
import com.example.newsappproject.ui.data.api.NewsApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitInstance{
    private const val BASE_URL = "https://newsapi.org/"

    val apiService: NewsApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)
    }

}