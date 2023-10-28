package com.example.alfagift.model

import com.google.gson.annotations.SerializedName

class ResultDiscoverMovies(
    @SerializedName("adult") var adult: Boolean? = false,
    @SerializedName("backdrop_path") var backdropPath: String? = null,
    @SerializedName("genre_ids") var genreIds: ArrayList<String> = arrayListOf(),
    @SerializedName("id") var id: String? = null,
    @SerializedName("original_language") var originalLanguage: String? = null,
    @SerializedName("original_title") var originalTitle: String? = null,
    @SerializedName("overview") var overview: String? = null,
    @SerializedName("popularity") var popularity: String? = null,
    @SerializedName("poster_path") var posterPath: String? = null,
    @SerializedName("release_date") var releaseDate: String? = null,
    @SerializedName("title") var title: String? = null,
    @SerializedName("video") var video: Boolean? = false,
    @SerializedName("vote_average") var voteAverage: String? = null,
    @SerializedName("vote_count") var voteCount: String? = null
)