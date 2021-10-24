package com.myapplication.di

import android.app.Application
import androidx.room.Room
import com.myapplication.db.ArticleDatabase
import com.myapplication.util.Constants.Companion.ARTICLE_DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideArticleDatabase(app: Application
    ): ArticleDatabase {
        return Room.databaseBuilder(
        app,
        ArticleDatabase::class.java,
        ARTICLE_DATABASE_NAME
    ).build()
    }

    @Singleton
    @Provides
    fun provideArticleDao(db: ArticleDatabase) = db.getArticleDao()


}



