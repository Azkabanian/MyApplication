package com.myapplication.di

import android.app.Application
import androidx.room.Room
import com.myapplication.api.NewsAPI
import com.myapplication.db.ArticleDatabase
import com.myapplication.repository.NewsRepository
import com.myapplication.util.Constants.Companion.ARTICLE_DATABASE_NAME
import com.myapplication.util.Constants.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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


    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()


    @Provides
    @Singleton
    fun provideNewsApi (retrofit: Retrofit) : NewsAPI =
        retrofit.create(NewsAPI::class.java)

}






