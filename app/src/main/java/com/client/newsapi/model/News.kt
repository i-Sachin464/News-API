package com.client.newsapi.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class News (
    @field:SerializedName("id") var id: String?,
    @field:SerializedName("title") var title: String?,
    @field:SerializedName("description") var description: String?,
    @field:SerializedName("urlToImage") var urlToImage: String?,
    @field:SerializedName("url") var url: String?,
    @field:SerializedName("publishedAt") var publishedAt: String?,
    @field:SerializedName("content") var content: String?,
    @field:SerializedName("author") var author: String?
) : Parcelable