package com.bookmyshow.data_layer.repository

import com.bookmyshow.core.NetworkProvider
import com.bookmyshow.data_layer.model.VenueListModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

/**
 * Created by Akshansh Dhing on 12/10/22.
 */

class ShowTimesRepository @Inject constructor(
    private val networkProvider: NetworkProvider
) {

    private val api: ShowTimesAPI
        get() = networkProvider.getApi(
            apiClass = ShowTimesAPI::class.java, baseUrl = "https://demo2782755.mockable.io"
        )

    fun fetchVenueList(): Flow<VenueListModel> = api.getShowTimes().flowOn(Dispatchers.IO)
}