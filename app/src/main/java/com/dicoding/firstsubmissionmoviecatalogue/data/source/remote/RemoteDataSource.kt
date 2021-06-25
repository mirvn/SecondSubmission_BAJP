package com.dicoding.firstsubmissionmoviecatalogue.data.source.remote

import com.dicoding.firstsubmissionmoviecatalogue.data.source.remote.api.ApiClient
import com.dicoding.firstsubmissionmoviecatalogue.data.source.remote.response.MovieResponse
import com.dicoding.firstsubmissionmoviecatalogue.data.source.remote.response.TvShowResponse
import com.dicoding.firstsubmissionmoviecatalogue.utils.EspressoHelper
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized
import retrofit2.await

class RemoteDataSource {
    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        @InternalCoroutinesApi
        fun getInstance(): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource()
            }
    }

    suspend fun getPlayingMovies(callback: LoadPlayingMoviesCallback) {
        EspressoHelper.incrementResource()
        ApiClient.instance.getPlayingMovies().await().result?.let {
            callback.movieReceived(it)
            EspressoHelper.decrementResource()
        }
    }

    suspend fun getDetailMovie(id: Int, callback: LoadDetailMovieCallback) {
        EspressoHelper.incrementResource()
        ApiClient.instance.getDetailMovie(id).await().let {
            callback.detailMovieReceived(it)
            EspressoHelper.decrementResource()
        }
    }

    suspend fun getPlayingTvShow(callback: LoadPlayingTvShow) {
        EspressoHelper.incrementResource()
        ApiClient.instance.getTvShowOnAir().await().result?.let {
            callback.tvShowReceived(it)
            EspressoHelper.decrementResource()
        }
    }

    suspend fun getDetailTvShow(id: Int, callback: LoadDetailTvShow) {
        EspressoHelper.incrementResource()
        ApiClient.instance.getDetailTvShow(id).await().let {
            callback.detailTvShowReceived(it)
            EspressoHelper.decrementResource()
        }
    }

    interface LoadDetailTvShow {
        fun detailTvShowReceived(tvShowResponse: TvShowResponse)
    }

    interface LoadPlayingTvShow {
        fun tvShowReceived(tvShowResponse: List<TvShowResponse>)
    }

    interface LoadDetailMovieCallback {
        fun detailMovieReceived(movieResponse: MovieResponse)
    }

    interface LoadPlayingMoviesCallback {
        fun movieReceived(movieResponse: List<MovieResponse>)
    }
}

