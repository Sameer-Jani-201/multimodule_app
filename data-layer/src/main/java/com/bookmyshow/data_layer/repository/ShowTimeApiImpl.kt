package com.bookmyshow.data_layer.repository

import com.bookmyshow.data_layer.model.VenueListModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ShowTimeApiImpl @Inject constructor(private val repository: ShowTimesRepository) {
    fun fetchShowTime(): Flow<VenueListModel> = repository.fetchVenueList()
}