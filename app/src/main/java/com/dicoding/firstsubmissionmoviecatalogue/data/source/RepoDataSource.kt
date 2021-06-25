package com.dicoding.firstsubmissionmoviecatalogue.data.source

import androidx.lifecycle.LiveData
import com.dicoding.firstsubmissionmoviecatalogue.model.DataModel

interface RepoDataSource {
    fun getPlayingMovies(): LiveData<List<DataModel>>
    fun getDetailMovie(movieId: Int): LiveData<DataModel>
    fun getPlayingTvShow(): LiveData<List<DataModel>>
    fun getDetailTvShow(tvShowId: Int): LiveData<DataModel>
}