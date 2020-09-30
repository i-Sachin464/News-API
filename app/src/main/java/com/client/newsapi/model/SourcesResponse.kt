package com.client.newsapi.model

import com.google.gson.annotations.SerializedName

data class SourcesResponse(
    @field:SerializedName("status") var status: String,
    @field:SerializedName("sources") var sources: List<Sources?>?
)