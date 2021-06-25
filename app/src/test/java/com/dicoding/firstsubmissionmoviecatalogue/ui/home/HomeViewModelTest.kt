package com.dicoding.firstsubmissionmoviecatalogue.ui.home

import junit.framework.TestCase
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class HomeViewModelTest {
    private lateinit var homeViewModel: HomeViewModel

    @Before
    fun setUp() {
        homeViewModel = HomeViewModel(mCatalogRepo)
    }

    @Test
    fun getListMovie() {
        val movies = homeViewModel.getListMovie()
        TestCase.assertNotNull(movies)
        assertEquals(10, movies.size)
    }

    @Test
    fun getListTvShow() {
        val tvShows = homeViewModel.getListTvShow()
        TestCase.assertNotNull(tvShows)
        assertEquals(10, tvShows.size)
    }
}