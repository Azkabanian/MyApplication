package com.myapplication.domain.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Entity(
    tableName = "articles"
)

@Parcelize
data class Article(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    val author: String?,
    val content: String?,
    val description: String?,
    val publishedAt: String?,
    val source:@RawValue Source?,
    val title: String?,
    val url: String,
    val urlToImage: String?
): Parcelable