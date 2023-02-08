package com.myapplication.data.repository

import com.myapplication.data.api.NewsAPI
import com.myapplication.data.db.ArticleDao
import com.myapplication.domain.models.Article
import com.myapplication.domain.models.NewsResponse
import com.myapplication.domain.repository.NewsRepository
import com.myapplication.ui.util.Resource
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val articleDao: ArticleDao,
    private val api: NewsAPI
) : NewsRepository {
    override suspend fun getBreakingNews(countryCode: String, pageNumber: Int) =
        api.getBreakingNews(countryCode, pageNumber)

    override suspend fun searchNews(searchQuery: String): Resource<NewsResponse> {
        val request = api.searchForNews(searchQuery)
        return if (!request.isSuccessful) {
            Resource.Error(request.toString())
        } else {
            val responseData = request.body()
            Resource.Success(responseData!!)
        }
    }

    override suspend fun upsert(article: Article) = articleDao.upsert(article)

    override suspend fun deleteArticle(article: Article) = articleDao.deleteArticle(article)

    override fun getSavedNews() = articleDao.getAllArticles()
}