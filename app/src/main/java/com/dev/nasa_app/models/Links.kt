package com.dev.nasa_app.models

import com.google.gson.annotations.SerializedName

data class Links (

    @SerializedName("href") var href   : String? = null,
    @SerializedName("rel") var rel    : String? = null,
    @SerializedName("render" ) var render : String? = null

)