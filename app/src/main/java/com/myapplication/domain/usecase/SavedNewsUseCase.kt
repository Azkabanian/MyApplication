package com.myapplication.domain.usecase

import com.myapplication.domain.repository.NewsRepository
import javax.inject.Inject

class SavedNewsUseCase @Inject constructor(
    private val repository: NewsRepository
) {
    fun getSavedNews() = repository.getSavedNews()
}