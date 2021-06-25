package com.dicoding.firstsubmissionmoviecatalogue.ui.detail

import com.dicoding.firstsubmissionmoviecatalogue.utils.DataDummy
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class DetailViewModelTest {

    private lateinit var viewModel: DetailViewModel
    private val dataDummyMovie = DataDummy.generateResponseDataMovie()[0]
    private val dataDummyTvShow = DataDummy.generateResponseDataTvShow()[0]
    private val movieId = dataDummyMovie.id
    private val tvShowId = dataDummyTvShow.id

    @Before
    fun setUp(){
        viewModel = DetailViewModel(mCatalogRepo)
        viewModel.setMovieId(movieId)
        viewModel.setTvShowId(tvShowId)
    }

    @Test
    fun getDetailMovieById() {
        val movie = viewModel.getDetailMovieById()
        assertNotNull(movie)
        assertEquals(dataDummyMovie.name, movie.name)
        assertEquals(dataDummyMovie.desc, movie.desc)
        assertEquals(dataDummyMovie.poster, movie.poster)
        assertEquals(dataDummyMovie.img_preview, movie.img_preview)
    }

    @Test
    fun getDetailTvShowById() {
        val tvShow = viewModel.getDetailTvShowById()
        assertNotNull(tvShow)
        assertEquals(dataDummyTvShow.name, tvShow.name)
        assertEquals(dataDummyTvShow.desc, tvShow.desc)
        assertEquals(dataDummyTvShow.poster, tvShow.poster)
        assertEquals(dataDummyTvShow.img_preview, tvShow.img_preview)
    }
}