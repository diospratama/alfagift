package com.example.alfagift.model

import com.google.gson.annotations.SerializedName

class ResponseMoviesVideos(

    @SerializedName("id") var id: Int? = null,
    @SerializedName("results") var results: ArrayList<ResultMoviesVideos> = arrayListOf()

)