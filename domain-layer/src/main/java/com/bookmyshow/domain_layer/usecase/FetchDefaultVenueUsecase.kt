package com.bookmyshow.domain_layer.usecase

import com.bookmyshow.data_layer.repository.ShowTimeApiImpl
import com.bookmyshow.domain_layer.model.VenuesModel
import com.bookmyshow.domain_layer.utils.Converter
import com.bookmyshow.domain_layer.utils.SortedMode
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

/**
 * This is Usecase to fetch venue details from repository class
 * ans sort the view model list based on request sort mode [SortedMode]
 */
class FetchDefaultVenueUsecase @Inject constructor(private val showTimeApi: ShowTimeApiImpl) {
    /**
     * @param sortedMode [SortedMode] as param based on it venue list will be sort Eg. by name or date
     */
    suspend operator fun invoke(sortedMode: SortedMode): Flow<VenuesModel> {
        return showTimeApi.fetchShowTime().flowOn(Dispatchers.IO).first().venues.asFlow().map {
            val listOfVenueShowTime = mutableListOf<VenuesModel.ShowTime>()
            val venueModel = VenuesModel(it.name, Converter.parseStringToDate(it.showDate)!!)
            it.showtimes.forEach {
                listOfVenueShowTime.add(venueModel.ShowTime(it.showTime, it.showDateCode))
            }
            venueModel.copy(list = listOfVenueShowTime)
        }.flowOn(Dispatchers.Default).toList().sortedWith(if (isSortedByName(sortedMode)) {
            compareBy { it.name }
        } else {
            compareBy { it.showDate }
        }).asFlow().flowOn(Dispatchers.Default)
    }
    
    private fun isSortedByName(sortedMode: SortedMode): Boolean {
        return when (sortedMode) {
            is SortedMode.NAME -> true
            is SortedMode.DATE -> false
        }
    }
}