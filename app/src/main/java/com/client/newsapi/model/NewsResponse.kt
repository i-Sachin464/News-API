package com.client.newsapi.model

import com.google.gson.annotations.SerializedName

data class NewsResponse(
    @field:SerializedName("status") var status: String,
    @field:SerializedName("total_results") var totalResults: Int,
    @field:SerializedName("articles") var articles: List<News?>?
)