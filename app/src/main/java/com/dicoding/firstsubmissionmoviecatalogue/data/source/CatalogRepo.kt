package com.dicoding.firstsubmissionmoviecatalogue.data.source

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dicoding.firstsubmissionmoviecatalogue.data.source.remote.RemoteDataSource
import com.dicoding.firstsubmissionmoviecatalogue.data.source.remote.response.MovieResponse
import com.dicoding.firstsubmissionmoviecatalogue.data.source.remote.response.TvShowResponse
import com.dicoding.firstsubmissionmoviecatalogue.model.DataModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized
import kotlinx.coroutines.launch
import kotlin.collections.ArrayList

class CatalogRepo private constructor(private val remoteDataSource: RemoteDataSource)
    : RepoDataSource{
    companion object{
        val TAG = CatalogRepo::class.java.simpleName
        @Volatile
        private var instance: CatalogRepo? = null

        @InternalCoroutinesApi
        fun getInstance(remoteDataSource: RemoteDataSource): CatalogRepo =
            instance?: synchronized(this){
                instance?: CatalogRepo(remoteDataSource)
            }
    }

    override fun getPlayingMovies(): LiveData<List<DataModel>> {
        val listMovieResult = MutableLiveData<List<DataModel>>()
        CoroutineScope(IO).launch {
            remoteDataSource.getPlayingMovies(object : RemoteDataSource.LoadPlayingMoviesCallback {
                override fun movieReceived(movieResponse: List<MovieResponse>) {
                    val movieList = ArrayList<DataModel>()
                    for (response in movieResponse) {
                        val movie = response.name?.let { name->
                            response.description?.let { desc ->
                                response.poster?.let { poster ->
                                    response.image?.let { image ->
                                        DataModel(
                                            response.id,
                                            name,
                                            desc,
                                            poster,
                                            image
                                        )
                                    }
                                }
                            }
                        }
                        if (movie != null) {
                            movieList.add(movie)
                        }
                        Log.d(TAG, "movieReceived-movieList:$movieList ")
                    }
                    listMovieResult.postValue(movieList)
                }

            })
        }
        return listMovieResult
    }

    override fun getDetailMovie(movieId: Int): LiveData<DataModel> {
        val movieResult = MutableLiveData<DataModel>()
        CoroutineScope(IO).launch {
            remoteDataSource.getDetailMovie(movieId, object : RemoteDataSource.LoadDetailMovieCallback {
                override fun detailMovieReceived(movieResponse: MovieResponse) {
                    val movieDetail = movieResponse.name?.let { name ->
                        movieResponse.poster?.let { poster ->
                            movieResponse.description?.let { descripstion ->
                                movieResponse.image?.let { image ->
                                    DataModel(
                                        movieResponse.id,
                                        name,
                                        poster,
                                        descripstion,
                                        image
                                    )
                                }
                            }
                        }
                    }
                    movieResult.postValue(movieDetail)
                    Log.d(TAG, "detailMovieReceived-movieResult:$movieResult ")
                }
            })
        }
        return movieResult
    }

    override fun getPlayingTvShow(): LiveData<List<DataModel>> {
        val listTvShowResult = MutableLiveData<List<DataModel>>()
        CoroutineScope(IO).launch {
            remoteDataSource.getPlayingTvShow(object : RemoteDataSource.LoadPlayingTvShow {
                override fun tvShowReceived(tvShowResponse: List<TvShowResponse>) {
                    val listTvShow = ArrayList<DataModel>()
                    for (response in tvShowResponse) {
                        val tvShow = response.name?.let { name ->
                            response.description?.let { description ->
                                response.poster?.let { poster ->
                                    response.image?.let { image ->
                                        DataModel(
                                            response.id,
                                            name,
                                            description,
                                            poster,
                                            image
                                        )
                                    }
                                }
                            }
                        }
                        if (tvShow != null) {
                            listTvShow.add(tvShow)
                        }
                        Log.d(TAG, "tvShowReceived-listTvShow:$listTvShow ")
                    }
                    listTvShowResult.postValue(listTvShow)
                }
            })
        }
        return listTvShowResult
    }

    override fun getDetailTvShow(tvShowId: Int): LiveData<DataModel> {
        val tvShowResult = MutableLiveData<DataModel>()
        CoroutineScope(IO).launch {
            remoteDataSource.getDetailTvShow(tvShowId, object : RemoteDataSource.LoadDetailTvShow {
                override fun detailTvShowReceived(tvShowResponse: TvShowResponse) {
                    val tvShowDetail = tvShowResponse.name?.let { name ->
                        tvShowResponse.image?.let { image ->
                            tvShowResponse.description?.let { description ->
                                tvShowResponse.poster?.let { posterr ->
                                    DataModel(
                                        tvShowResponse.id,
                                        name,
                                        image,
                                        description,
                                        posterr,
                                    )
                                }
                            }
                        }
                    }
                    tvShowResult.postValue(tvShowDetail)
                    Log.d(TAG, "detailTvShowReceived-tvShowResult:$tvShowResult ")
                }
            })
        }
        return tvShowResult
    }


}