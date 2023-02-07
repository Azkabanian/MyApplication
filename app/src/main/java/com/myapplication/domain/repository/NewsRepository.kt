package com.myapplication.domain.repository

import androidx.lifecycle.LiveData
import com.myapplication.domain.models.Article
import com.myapplication.domain.models.NewsResponse
import retrofit2.Response

interface NewsRepository{
    suspend fun getBreakingNews(countryCode: String, pageNumber: Int): Response<NewsResponse>

    suspend fun searchNews(searchQuery: String, pageNumber: Int): Response<NewsResponse>
    suspend fun upsert(article: Article): Long

    suspend fun deleteArticle(article: Article)

    fun getSavedNews(): LiveData<List<Article>>
}