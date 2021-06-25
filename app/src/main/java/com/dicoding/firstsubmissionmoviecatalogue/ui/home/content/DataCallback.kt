package com.dicoding.firstsubmissionmoviecatalogue.ui.home.content

import com.dicoding.firstsubmissionmoviecatalogue.model.DataModel

interface DataCallback {
    fun onItemClicked(data: DataModel)
}