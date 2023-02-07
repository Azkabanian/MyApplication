package com.myapplication.domain.usecase

import com.myapplication.domain.repository.NewsRepository
import javax.inject.Inject

class GetBreakingNewsUseCase @Inject constructor(
    private val repository: NewsRepository
) {
    suspend operator fun invoke(countryCode: String, pageNumber: Int)
    = repository.getBreakingNews(countryCode, pageNumber)
}