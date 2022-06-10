package com.myapplication.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myapplication.models.Article
import com.myapplication.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticleViewModel @Inject constructor(
    val newsRepository: NewsRepository
): ViewModel() {

    fun saveArticle(article: Article) = viewModelScope.launch {
        newsRepository.upsert(article)
    }
}