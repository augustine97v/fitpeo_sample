package com.fitpeo.sample.ui.home

import retrofit2.http.*

/**
 * @author Augustine on 01/03/23.
 * */
interface HomeService {

    @GET("photos/")
    suspend fun getData(
    ): List<HomeResponseNetworkEntity>

}

