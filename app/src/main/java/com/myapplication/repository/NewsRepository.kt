package com.myapplication.repository

import com.myapplication.api.NewsAPI
import com.myapplication.db.ArticleDao
import com.myapplication.models.Article
import javax.inject.Inject

class NewsRepository @Inject constructor (
    private val articleDao: ArticleDao,
    private val api: NewsAPI
    ) {
    suspend fun getBreakingNews(countryCode: String, pageNumber: Int) =
        api.getBreakingNews(countryCode, pageNumber)

    suspend fun searchNews(searchQuery: String, pageNumber: Int) =
        api.searchForNews(searchQuery, pageNumber)

    suspend fun upsert(article: Article) = articleDao.upsert(article)

    suspend fun deleteArticle(article: Article) = articleDao.deleteArticle(article)

    fun getSavedNews() = articleDao.getAllArticles()
}