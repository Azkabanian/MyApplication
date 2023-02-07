package com.myapplication.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myapplication.domain.models.Article
import com.myapplication.domain.usecase.SaveArticleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticleViewModel @Inject constructor(
    private val saveArticleUseCase: SaveArticleUseCase,
): ViewModel() {

    fun saveArticle(article: Article) = viewModelScope.launch() {
        saveArticleUseCase.invoke(article)
    }
}