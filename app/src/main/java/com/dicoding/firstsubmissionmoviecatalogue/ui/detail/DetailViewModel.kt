package com.dicoding.firstsubmissionmoviecatalogue.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.firstsubmissionmoviecatalogue.data.source.CatalogRepo
import com.dicoding.firstsubmissionmoviecatalogue.model.DataModel
import com.dicoding.firstsubmissionmoviecatalogue.utils.DataDummy

class DetailViewModel(private val mCatalogRepo: CatalogRepo) : ViewModel() {

    fun getDetailMovieById(idMovie: Int): LiveData<DataModel> {
        return mCatalogRepo.getDetailMovie(idMovie)
    }

    fun getDetailTvShowById(idTvShow: Int): LiveData<DataModel> {
        return mCatalogRepo.getDetailTvShow(idTvShow)
    }
}