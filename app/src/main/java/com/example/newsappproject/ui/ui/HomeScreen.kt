package com.example.newsappproject.ui.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.newsappproject.ui.data.model.Article


@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel,
    navHostController: NavHostController
) {
    val newsList by viewModel.news.collectAsState(initial = emptyList())

    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(newsList) { news ->
                NewsCard(news = news, viewModel = viewModel , navHostController = navHostController)
            }
        }
    }
}

@Composable
fun NewsCard(news: Article, viewModel: HomeViewModel , navHostController: NavHostController) {
    Card(
        onClick ={
            navHostController.navigate("DetailScreen/${news.title}")
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(text = news.title ?: "Başlık Yok",)
            Spacer(modifier = Modifier.height(8.dp))
            AsyncImage(
                model = news.urlToImage,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
            Text(text = news.description ?: "Açıklama Yok")
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Yazar: ${news.author ?: "Bilinmiyor"}")
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Yayınlanma Tarihi: ${news.publishedAt ?: "Tarih Yok"}")
        }
    }
}
