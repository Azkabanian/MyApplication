package com.myapplication.domain.usecase

import com.myapplication.domain.models.Article
import com.myapplication.domain.repository.NewsRepository
import javax.inject.Inject

class SaveArticleUseCase @Inject constructor(
    private val repository: NewsRepository
) {
    suspend operator fun invoke(article: Article) = repository.upsert(article)
}