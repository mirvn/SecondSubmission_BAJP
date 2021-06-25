package com.dicoding.firstsubmissionmoviecatalogue.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class DataModel(
        var id: Int,
        var name: String,
        var desc: String,
        var poster: String,
        var img_preview: String
) : Parcelable