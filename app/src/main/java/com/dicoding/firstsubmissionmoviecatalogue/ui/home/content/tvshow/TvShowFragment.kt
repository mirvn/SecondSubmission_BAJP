package com.dicoding.firstsubmissionmoviecatalogue.ui.home.content.tvshow

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.firstsubmissionmoviecatalogue.databinding.FragmentTvShowBinding
import com.dicoding.firstsubmissionmoviecatalogue.model.DataModel
import com.dicoding.firstsubmissionmoviecatalogue.ui.detail.DetailActivity
import com.dicoding.firstsubmissionmoviecatalogue.ui.home.HomeViewModel
import com.dicoding.firstsubmissionmoviecatalogue.ui.home.content.DataAdapter
import com.dicoding.firstsubmissionmoviecatalogue.ui.home.content.DataCallback
import com.dicoding.firstsubmissionmoviecatalogue.utils.Helper.TYPE_TVSHOW
import com.dicoding.firstsubmissionmoviecatalogue.viewmodel.ViewModelFactory
import kotlinx.coroutines.InternalCoroutinesApi

class TvShowFragment : Fragment(), DataCallback{

    private lateinit var viewModel: HomeViewModel
    lateinit var binding: FragmentTvShowBinding
    companion object{
        var TAG = FragmentTvShowBinding::class.java.simpleName
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTvShowBinding.inflate(inflater,container,false)
        return binding.root
    }

    @InternalCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView()
        val factory = ViewModelFactory.getInstance()
        activity?.let {
            viewModel = ViewModelProvider(it, factory)[HomeViewModel::class.java]
        }
        viewModel.getListTvShow().observe(viewLifecycleOwner, { listTvShow ->
            Log.d(TAG, "onViewCreated-listTvShow:$listTvShow ")
            binding.rvTvshow.adapter?.let {
                when (it) {
                    is DataAdapter -> it.setData(listTvShow)
                }
            }
        })
    }

    private fun setRecyclerView() {
        binding.rvTvshow.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = DataAdapter(this@TvShowFragment)
        }
    }

    override fun onItemClicked(data: DataModel) {
        startActivity(
            Intent(
                context,
                DetailActivity::class.java
            )
                .putExtra(DetailActivity.EXTRA_DATA, data.id)
                .putExtra(DetailActivity.EXTRA_TYPE, TYPE_TVSHOW)
        )
    }
}