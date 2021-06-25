package com.dicoding.firstsubmissionmoviecatalogue.data.source.remote.api

import com.dicoding.firstsubmissionmoviecatalogue.BuildConfig
import com.dicoding.firstsubmissionmoviecatalogue.data.source.remote.response.MovieResponse
import com.dicoding.firstsubmissionmoviecatalogue.data.source.remote.response.ResponseList
import com.dicoding.firstsubmissionmoviecatalogue.data.source.remote.response.TvShowResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServices {

    @GET("movie/now_playing")
    fun getPlayingMovies(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): Call<ResponseList<MovieResponse>>

    @GET("movie/{movie_id}")
    fun getDetailMovie(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ) : Call<MovieResponse>

    @GET("tv/on_the_air")
    fun getTvShowOnAir(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ) : Call<ResponseList<TvShowResponse>>

    @GET("tv/{tv_id}")
    fun getDetailTvShow(
        @Path("tv_id") tvShowId: Int,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ) : Call<TvShowResponse>

}