package com.dicoding.firstsubmissionmoviecatalogue.ui.home

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.dicoding.firstsubmissionmoviecatalogue.R
import com.dicoding.firstsubmissionmoviecatalogue.ui.home.content.movie.MovieFragment
import com.dicoding.firstsubmissionmoviecatalogue.ui.home.content.tvshow.TvShowFragment

class SectionPagerAdapter(private val context: Context, fm:FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){

    companion object{
        @StringRes
        private val title = intArrayOf(R.string.tab_title_movie,R.string.tab_title_tvshow)
    }

    override fun getCount(): Int =2

    override fun getItem(position: Int): Fragment =
        when(position){
            0-> MovieFragment()
            1-> TvShowFragment()
            else -> Fragment()
        }

    override fun getPageTitle(position: Int): CharSequence? =
        context.resources.getString(title[position])
}