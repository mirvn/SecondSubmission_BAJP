package com.dicoding.firstsubmissionmoviecatalogue.ui.detail

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.dicoding.firstsubmissionmoviecatalogue.R
import com.dicoding.firstsubmissionmoviecatalogue.ui.home.HomeActivity
import com.dicoding.firstsubmissionmoviecatalogue.utils.DataDummy
import org.junit.Rule
import org.junit.Test

class DetailActivityTest {
    private val dummyMovie = DataDummy.generateResponseDataMovie()
    private val dummyTv = DataDummy.generateResponseDataTvShow()

    @get:Rule
    var activityRule = ActivityScenarioRule(HomeActivity::class.java)

    @Test
    fun loadDetailMovie() {
        onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.tv_detail_name)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_name)).check(matches(withText(dummyMovie[0].name)))
        onView(withId(R.id.tv_detail_desc)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_desc)).check(matches(withText(dummyMovie[0].desc)))
    }

    @Test
    fun loadDetailTvShows() {
        onView(withId(R.id.nav_tvshow)).perform(click())
        onView(withId(R.id.rv_tvshow)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.tv_detail_name)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_name)).check(matches(withText(dummyTv[0].name)))
        onView(withId(R.id.tv_detail_desc)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_desc)).check(matches(withText(dummyTv[0].desc)))
    }
}