package com.dicoding.firstsubmissionmoviecatalogue.ui.detail

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import com.dicoding.firstsubmissionmoviecatalogue.R
import com.dicoding.firstsubmissionmoviecatalogue.databinding.ActivityDetailBinding
import com.dicoding.firstsubmissionmoviecatalogue.model.DataModel
import com.dicoding.firstsubmissionmoviecatalogue.utils.Helper.TYPE_MOVIE
import com.dicoding.firstsubmissionmoviecatalogue.utils.Helper.setImageWithGlide
import com.dicoding.firstsubmissionmoviecatalogue.viewmodel.ViewModelFactory
import kotlinx.coroutines.InternalCoroutinesApi
import com.dicoding.firstsubmissionmoviecatalogue.utils.Helper.API_IMAGE_ENDPOINT
import com.dicoding.firstsubmissionmoviecatalogue.utils.Helper.ENDPOINT_POSTER_SIZE_W780
import com.dicoding.firstsubmissionmoviecatalogue.utils.Helper.ENDPOINT_POSTER_SIZE_W185
import com.dicoding.firstsubmissionmoviecatalogue.utils.Helper.TYPE_TVSHOW

class DetailActivity : AppCompatActivity() {

    lateinit var bindingActivity: ActivityDetailBinding

    companion object{
        const val EXTRA_DATA = "extra_data"
        const val EXTRA_TYPE = "extra_type"
    }

    private lateinit var result: DataModel

    @InternalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*bindingActivity = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(bindingActivity.root)*/
        setContentView(R.layout.activity_detail)

        val factory = ViewModelFactory.getInstance()
        val viewModel = ViewModelProvider(
            this,
            factory
        )[DetailViewModel::class.java]

        setupToolbar()

        val id = intent.getStringExtra(EXTRA_DATA)
        val type = intent.getStringExtra(EXTRA_TYPE)

        if (type.equals(TYPE_MOVIE, ignoreCase = true)) {
            setupToolbarTitle(resources.getString(R.string.toolbar_title_detail_movie))

            if (id != null) {
                viewModel.getDetailMovieById(id.toInt()).observe(this, {
                    displayData(it)
                })
            }
        }
        else if (type.equals(TYPE_TVSHOW, ignoreCase = true)){
            if (id != null) {
                viewModel.getDetailTvShowById(id.toInt()).observe(this, {
                    displayData(it)
                })
            }
        }
    }

    private fun displayData(data: DataModel?) {
        findViewById<TextView>(R.id.tv_detail_name).text = data?.name
        findViewById<TextView>(R.id.tv_detail_desc).text = data?.desc
        setImageWithGlide(this,
            API_IMAGE_ENDPOINT+ ENDPOINT_POSTER_SIZE_W185 + data?.poster,
            findViewById(R.id.img_detail_poster) )
        setImageWithGlide(this,
            API_IMAGE_ENDPOINT+ ENDPOINT_POSTER_SIZE_W780+data?.img_preview,
            findViewById(R.id.img_detail_hightlight)
        )
    }

    private fun setupToolbar() {
        /*setSupportActionBar(bindingActivity.detailToolbar)*/
        setSupportActionBar(findViewById(R.id.detail_toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        /*bindingActivity.collapsingToolbar.setExpandedTitleColor(Color.TRANSPARENT)*/
    }

    private fun setupToolbarTitle(title: String) {
        supportActionBar?.title = title
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}