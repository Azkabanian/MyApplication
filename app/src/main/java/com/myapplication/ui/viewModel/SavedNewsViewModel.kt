package com.myapplication.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myapplication.domain.models.Article
import com.myapplication.domain.usecase.DeleteArticleUseCase
import com.myapplication.domain.usecase.SaveArticleUseCase
import com.myapplication.domain.usecase.SavedNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SavedNewsViewModel @Inject constructor(
    private val savedNewsUseCase: SavedNewsUseCase,
    private val deleteArticleUseCase: DeleteArticleUseCase,
    private val saveArticleUseCase: SaveArticleUseCase
) : ViewModel() {

    fun getSavedNews() = savedNewsUseCase.getSavedNews()

    fun deleteArticle(article: Article) = viewModelScope.launch {
        deleteArticleUseCase.invoke(article)
    }

    fun saveArticle(article: Article) = viewModelScope.launch {
        saveArticleUseCase.invoke(article)
    }
}