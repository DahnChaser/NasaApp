package com.dev.nasa_app.models

import com.google.gson.annotations.SerializedName

data class GalaxyCollection (

    @SerializedName("version")
    var version  : String?          = null,
    @SerializedName( "items")
    var mGalaxyModelWrapper : ArrayList<GalaxyModelWrapper> = arrayListOf()

)