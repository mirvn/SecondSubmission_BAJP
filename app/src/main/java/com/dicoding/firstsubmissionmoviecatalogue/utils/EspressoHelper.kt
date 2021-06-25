package com.dicoding.firstsubmissionmoviecatalogue.utils

import androidx.test.espresso.idling.CountingIdlingResource

object EspressoHelper {
    private const val RESOURCE = "GLOBAL"
    val espressoTestIdlingResource = CountingIdlingResource(RESOURCE)

    fun decrementResource(){
        espressoTestIdlingResource.decrement()
    }
    fun incrementResource(){
        espressoTestIdlingResource.increment()
    }
}