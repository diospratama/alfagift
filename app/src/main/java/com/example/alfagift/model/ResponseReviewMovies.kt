package com.example.alfagift.model

import com.google.gson.annotations.SerializedName

class ResponseReviewMovies(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("page") var page: Int? = null,
    @SerializedName("results") var results: ArrayList<ResultReviewMovies> = arrayListOf(),
    @SerializedName("total_pages") var totalPages: Int? = null,
    @SerializedName("total_results") var totalResults: Int? = null

)