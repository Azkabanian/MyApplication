package com.myapplication.domain.usecase

import com.myapplication.domain.repository.NewsRepository
import javax.inject.Inject

class SearchNewsUseCase @Inject constructor(
    private val repository: NewsRepository
) {
    suspend operator fun invoke(pageNumber: String, searchQuery: Int)
    = repository.searchNews(pageNumber, searchQuery)
}