package com.dev.nasa_app.models

import com.google.gson.annotations.SerializedName


data class GalaxyModelWrapper (

    @SerializedName("href")
    var href  : String?          = null,
    @SerializedName("data")
    var data  : ArrayList<NasaGalaxyModel>  = arrayListOf(),
    @SerializedName("links")
    var links : ArrayList<Links> = arrayListOf()

)
