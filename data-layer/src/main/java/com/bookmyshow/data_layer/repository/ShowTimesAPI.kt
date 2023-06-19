package com.bookmyshow.data_layer.repository

import com.bookmyshow.data_layer.model.VenueListModel
import retrofit2.http.GET
import kotlinx.coroutines.flow.Flow

/**
 * Created by Akshansh Dhing on 12/10/22.
 */
interface ShowTimesAPI {

    @GET("/movie_showtimes")
    fun getShowTimes(): Flow<VenueListModel>
}