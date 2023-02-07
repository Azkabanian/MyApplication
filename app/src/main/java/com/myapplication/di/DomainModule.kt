package com.myapplication.di

import com.myapplication.data.api.NewsAPI
import com.myapplication.data.db.ArticleDao
import com.myapplication.data.repository.NewsRepositoryImpl
import com.myapplication.domain.repository.NewsRepository
import com.myapplication.domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Provides
    @Singleton
    fun provideNewsRepository(articleDao: ArticleDao, api: NewsAPI): NewsRepository =
        NewsRepositoryImpl(articleDao, api)

    @Provides
    @Singleton
    fun provideSaveArticleUseCase(repository: NewsRepository): SaveArticleUseCase =
        SaveArticleUseCase(repository)

    @Provides
    @Singleton
    fun provideGetBreakingNewsUseCase(repository: NewsRepository): GetBreakingNewsUseCase =
        GetBreakingNewsUseCase(repository)

    @Provides
    @Singleton
    fun provideSavedNewsUseCase(repository: NewsRepository): SavedNewsUseCase =
        SavedNewsUseCase(repository)

    @Provides
    @Singleton
    fun provideDeleteArticleUseCase(repository: NewsRepository): DeleteArticleUseCase =
        DeleteArticleUseCase(repository)

    @Provides
    @Singleton
    fun provideSearchNewsUseCase(repository: NewsRepository): SearchNewsUseCase =
        SearchNewsUseCase(repository)
}