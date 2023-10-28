package com.example.alfagift.api

import com.example.alfagift.commons.Constant.Header.accept
import com.example.alfagift.model.ResponseDiscoverMovies
import com.example.alfagift.model.ResponseMoviesVideos
import com.example.alfagift.model.ResponseReviewMovies
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ConnectionService {

    @Headers(
        "accept:$accept",
    )
    @GET("discover/movie")
    fun getListMovie(
        @Header("Authorization") token: String,
        @Query("include_adult") adult: Boolean,
        @Query("include_video") video: Boolean,
        @Query("language") language: String,
        @Query("page") page: Int,
        @Query("sort_by") sortBy: String

    ): Single<ResponseDiscoverMovies>

    @Headers(
        "accept:$accept",
    )
    @GET("movie/{movie_id}/videos")
    fun getMovieTrailer(
        @Header("Authorization") token: String,
        @Path("movie_id") movie: Int,
        @Query("language") language: String
    ): Single<ResponseMoviesVideos>


    @Headers(
        "accept:$accept",
    )
    @GET("movie/{movie_id}/reviews")
    fun getMovieReviews(
        @Header("Authorization") token: String,
        @Path("movie_id") movie: Int,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Single<ResponseReviewMovies>


}