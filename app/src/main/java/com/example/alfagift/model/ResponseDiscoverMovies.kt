package com.example.alfagift.model

import com.google.gson.annotations.SerializedName

data class ResponseDiscoverMovies(
    @SerializedName("page") var page: String? = null,
    @SerializedName("results") var results: ArrayList<ResultDiscoverMovies> = arrayListOf(),
    @SerializedName("total_pages") var totalPages: String? = null,
    @SerializedName("total_results") var totalResults: String? = null
)