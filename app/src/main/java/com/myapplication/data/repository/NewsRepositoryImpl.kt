package com.myapplication.data.repository

import com.myapplication.data.api.NewsAPI
import com.myapplication.data.db.ArticleDao
import com.myapplication.domain.models.Article
import com.myapplication.domain.repository.NewsRepository
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor (
    private val articleDao: ArticleDao,
    private val api: NewsAPI
    ) : NewsRepository
{
    override suspend fun getBreakingNews(countryCode: String, pageNumber: Int) =
        api.getBreakingNews(countryCode, pageNumber)

    override suspend fun searchNews(searchQuery: String, pageNumber: Int) =
        api.searchForNews(searchQuery, pageNumber)

    override suspend fun upsert(article: Article) = articleDao.upsert(article)

    override suspend fun deleteArticle(article: Article) = articleDao.deleteArticle(article)

    override fun getSavedNews() = articleDao.getAllArticles()


}