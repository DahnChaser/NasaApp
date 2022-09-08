package com.dev.nasa_app.models

import com.google.gson.annotations.SerializedName

data class NasaGalaxyModel (

    @SerializedName("center"       )
    var center      : String?           = null,
    @SerializedName("title"        )
    var title       : String?           = null,
    @SerializedName("location"     )
    var location    : String?           = null,
    @SerializedName( "nasa_id"      )
    var nasaId      : String?           = null,
    @SerializedName( "date_created" )
    var dateCreated : String?           = null,
    @SerializedName( "media_type"   )
    var mediaType   : String?           = null,
    @SerializedName( "description"  )
    var description : String?           = null

)

 