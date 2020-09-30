package com.client.newsapi.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class Sources(
    @field:SerializedName("id") var id: String?,
    @field:SerializedName("name") var name: String?,
    @field:SerializedName("description") var description: String?,
    @field:SerializedName("url") var url: String?,
    @field:SerializedName("category") var category: String?,
    @field:SerializedName("language") var language: String?,
    @field:SerializedName("country") var country: String?
) : Parcelable