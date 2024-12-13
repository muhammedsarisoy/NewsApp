package com.example.newsappproject.ui.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.newsappproject.ui.data.api.NewsApi
import com.example.newsappproject.ui.data.model.Article
import com.example.newsappproject.ui.data.model.NewsResponse
import com.example.newsappproject.ui.data.network.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {

    private val newsapiService = RetrofitInstance.apiService
    private val _news = MutableStateFlow<List<Article>>(emptyList())
    val news: StateFlow<List<Article>> = _news

    init {
        fetchNews()
    }

    private fun fetchNews() {
        val call = newsapiService.getTopHeadlines("us", "b8fc31a2fbdf4ca789a426aba3abb350")

        call.enqueue(object : Callback<NewsResponse> {
            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                if (response.isSuccessful) {
                    _news.value = response.body()?.articles ?: emptyList()
                } else {
                    _news.value = emptyList()
                }
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                _news.value = emptyList()
            }
        })
    }
}
