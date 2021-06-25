package com.dicoding.firstsubmissionmoviecatalogue.ui.home.content.movie

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.firstsubmissionmoviecatalogue.databinding.FragmentMovieBinding
import com.dicoding.firstsubmissionmoviecatalogue.model.DataModel
import com.dicoding.firstsubmissionmoviecatalogue.ui.detail.DetailActivity
import com.dicoding.firstsubmissionmoviecatalogue.ui.home.HomeViewModel
import com.dicoding.firstsubmissionmoviecatalogue.ui.home.content.DataAdapter
import com.dicoding.firstsubmissionmoviecatalogue.ui.home.content.DataCallback
import com.dicoding.firstsubmissionmoviecatalogue.utils.Helper.TYPE_MOVIE
import com.dicoding.firstsubmissionmoviecatalogue.viewmodel.ViewModelFactory
import kotlinx.coroutines.InternalCoroutinesApi


class MovieFragment : Fragment(),
    DataCallback{

    private lateinit var viewModel: HomeViewModel
    lateinit var binding:FragmentMovieBinding
    companion object{
        var TAG = MovieFragment::class.java.simpleName
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentMovieBinding.inflate(inflater,container,false)
        return binding.root
    }

    @InternalCoroutinesApi
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setRecyclerView()
        val factory = ViewModelFactory.getInstance()
        activity?.let {
            viewModel = ViewModelProvider( it, factory)[HomeViewModel::class.java]
        }
        viewModel.getListMovie().observe(viewLifecycleOwner, { listMovie ->
            Log.d(TAG, "onActivityCreated-listMovie:$listMovie ")
            binding.rvMovie.adapter?.let {
                when (it) {
                    is DataAdapter -> it.setData(listMovie)
                }
            }
        })
    }

    private fun setRecyclerView() {
        binding.rvMovie.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = DataAdapter(this@MovieFragment)

        }
    }

    override fun onItemClicked(data: DataModel) {
        startActivity(
            Intent(
                context,
                DetailActivity::class.java
            )
                .putExtra(DetailActivity.EXTRA_DATA, data.id)
                .putExtra(DetailActivity.EXTRA_TYPE, TYPE_MOVIE)
        )
    }
    }