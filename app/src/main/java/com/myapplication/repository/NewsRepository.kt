package com.myapplication.repository

import com.myapplication.api.RetrofitInstance
import com.myapplication.db.ArticleDao
import com.myapplication.models.Article
import javax.inject.Inject

class NewsRepository @Inject constructor (
    val articleDao: ArticleDao
    ) {
    suspend fun getBreakingNews(countryCode: String, pageNumber: Int) =
        RetrofitInstance.api.getBreakingNews(countryCode, pageNumber)

    suspend fun searchNews(searchQuery: String, pageNumber: Int) =
        RetrofitInstance.api.searchForNews(searchQuery, pageNumber)

    suspend fun upsert(article: Article) = articleDao.upsert(article)

    suspend fun deleteArticle(article: Article) = articleDao.deleteArticle(article)

    fun getSavedNews() = articleDao.getAllArticles()
}