package com.example.newsappproject.ui.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.newsappproject.ui.data.model.Article


@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun DetailScreen(
    modifier: Modifier,
    viewModel: HomeViewModel,
    navHostController: NavHostController,
    newsId: String
) {

    val news = viewModel.news.value.find { it.title == newsId }


    if (news != null) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = news.title ?: "Başlık Yok",
                modifier = Modifier.padding(bottom = 8.dp),
                fontSize = 24.sp
            )

            Spacer(modifier = Modifier.height(32.dp))

            AsyncImage(
                model = news.urlToImage,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))
            Text(text = news.description ?: "Açıklama Yok")
            Spacer(modifier = Modifier.height(32.dp))
            Text(text = "Yazar: ${news.author ?: "Bilinmiyor"}")
            Spacer(modifier = Modifier.height(32.dp))
            Text(text = "Yayınlanma Tarihi: ${news.publishedAt ?: "Tarih Yok"}")
            Spacer(modifier = Modifier.height(32.dp))
        }
    } else {
        Text(text = "Haber bulunamadı", modifier = Modifier.padding(16.dp))
    }
}
