package com.dev.nasa_app.viewmodel

import com.dev.nasa_app.models.GalaxyCollection
import com.google.gson.annotations.SerializedName

data class ResponseWrapper (

    @SerializedName("collection")
    var collection : GalaxyCollection? = GalaxyCollection()

)