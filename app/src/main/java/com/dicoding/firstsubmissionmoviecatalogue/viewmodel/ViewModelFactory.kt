package com.dicoding.firstsubmissionmoviecatalogue.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.firstsubmissionmoviecatalogue.data.source.CatalogRepo
import com.dicoding.firstsubmissionmoviecatalogue.dependencyinjection.Injection
import com.dicoding.firstsubmissionmoviecatalogue.ui.detail.DetailViewModel
import com.dicoding.firstsubmissionmoviecatalogue.ui.home.HomeViewModel
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

class ViewModelFactory private constructor(private val mCatalogRepo: CatalogRepo):
    ViewModelProvider.NewInstanceFactory(){

        @InternalCoroutinesApi
        companion object{
            @Volatile
            private var instance: ViewModelFactory? = null

            fun getInstance() : ViewModelFactory =
                instance?: synchronized(this){
                    instance?: ViewModelFactory(Injection.catalogRepositoryProvider())
                }
        }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when{
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(mCatalogRepo) as T
            }
            modelClass.isAssignableFrom(DetailViewModel::class.java)-> {
                DetailViewModel(mCatalogRepo) as T
            }
            else -> throw Throwable("${modelClass.name} not recognized")
        }
    }
}