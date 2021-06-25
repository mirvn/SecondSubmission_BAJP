package com.dicoding.firstsubmissionmoviecatalogue.ui.home
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.dicoding.firstsubmissionmoviecatalogue.R
import com.dicoding.firstsubmissionmoviecatalogue.databinding.ActivityHomeBinding
import com.dicoding.firstsubmissionmoviecatalogue.viewmodel.ViewModelFactory
import kotlinx.coroutines.InternalCoroutinesApi

class HomeActivity : AppCompatActivity() {

    private lateinit var viewModel: HomeViewModel
    lateinit var binding: ActivityHomeBinding

    @InternalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = ViewModelFactory.getInstance()
        viewModel = ViewModelProvider(this@HomeActivity, factory
        )[HomeViewModel::class.java]

        setToolbar()
        setViewPager()
    }

    private fun setViewPager() {
       val adapter = SectionPagerAdapter(this, supportFragmentManager)
        binding.mainViewPager.adapter = adapter
        binding.mainTablayout.setupWithViewPager(binding.mainViewPager)
    }

    private fun setToolbar() {
        setSupportActionBar(binding.mainToolbar)
        supportActionBar?.elevation = 8f
        supportActionBar?.title = getString(R.string.app_name)
    }
}