package com.dicoding.firstsubmissionmoviecatalogue.dependencyinjection

import com.dicoding.firstsubmissionmoviecatalogue.data.source.CatalogRepo
import com.dicoding.firstsubmissionmoviecatalogue.data.source.remote.RemoteDataSource
import kotlinx.coroutines.InternalCoroutinesApi

object Injection {
    @InternalCoroutinesApi
    fun catalogRepositoryProvider(): CatalogRepo{
        val remoteDataSource = RemoteDataSource.getInstance()
        return CatalogRepo.getInstance(remoteDataSource)
    }
}