package com.fitpeo.sample.ui.home

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


/**
 * @author Augustine on 01/03/23.
 * */
data class HomeResponseNetworkEntity(

    @SerializedName("albumId")
    @Expose
    var albumId: String? = null,

    @SerializedName("title")
    @Expose
    var title: String? = null,

    @SerializedName("url")
    @Expose
    var url: String? = null,

    @SerializedName("thumbnailUrl")
    @Expose
    var thumbnailUrl: String? = null,

//    var data: List<Data>? = null

)



