package com.fitpeo.sample.ui.home

import com.fitpeo.sample.utils.ApplicationSettings
import com.fitpeo.sample.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject


/**
 * @author Augustine on 01/03/23.
 * */

class HomeRepository
@Inject constructor(
    private val retrofit: HomeService
) {

    suspend fun getHomeRecyclerData(

    ): Flow<Resource<List<HomeResponseNetworkEntity>>> = flow {
        emit(Resource.loading())
        try {
            val value = retrofit.getData()
            emit(Resource.success(value))
        } catch (e: Exception) {
            emit(Resource.error(e.toString(), null))
        }
    }


}