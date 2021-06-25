package com.dicoding.firstsubmissionmoviecatalogue.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.firstsubmissionmoviecatalogue.data.source.CatalogRepo
import com.dicoding.firstsubmissionmoviecatalogue.model.DataModel
import com.dicoding.firstsubmissionmoviecatalogue.utils.DataDummy

class HomeViewModel(private var mCatalogRepo: CatalogRepo) : ViewModel() {
    fun getListMovie(): LiveData<List<DataModel>> = mCatalogRepo.getPlayingMovies()

    fun getListTvShow(): LiveData<List<DataModel>> = mCatalogRepo.getPlayingTvShow()
}