package com.bookmyshow.common_ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bookmyshow.common_ui.events.VenueListEvent
import com.bookmyshow.domain_layer.usecase.FetchDefaultVenueUsecase
import com.bookmyshow.common_ui.state.FeatureOneUiState
import com.bookmyshow.common_ui.state.FeatureTwoUiState
import com.bookmyshow.domain_layer.model.VenuesModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

/**
 * This is common view model for venue screen.
 * @param fetchDefaultVenueUsecase Feature Venue Usecase as param
 */
class CommonVenueViewModel constructor(val fetchDefaultVenueUsecase: FetchDefaultVenueUsecase) :
    ViewModel() {

    private val _venueListState: MutableStateFlow<FeatureOneUiState> = MutableStateFlow(
        FeatureOneUiState()
    )

    private val _selectedVenueState: MutableStateFlow<FeatureTwoUiState> =
        MutableStateFlow(FeatureTwoUiState(null))
    val selectedVenueState: StateFlow<FeatureTwoUiState> = _selectedVenueState.asStateFlow()


    private var originalVenueList = ArrayList<VenuesModel>()
    private var itemClickPos = -1

    fun itemClick(pos: Int) {
        itemClickPos = pos
        _selectedVenueState.value = selectedVenueState.value.copy(
            selectedVenue = originalVenueList[itemClickPos]
        )
    }

    val venueListState: StateFlow<FeatureOneUiState> = _venueListState.asStateFlow()

    private fun fetchVenueDetails() {
        viewModelScope.launch {
            fetchDefaultVenueUsecase(venueListState.value.sortingMode).onStart {

            }.onCompletion {
                if (originalVenueList.isEmpty()) {
                    onEvent(VenueListEvent.EmptyEvent(true))
                } else {
                    onEvent(VenueListEvent.EmptyEvent(false))
                    onEvent(VenueListEvent.NewDataEvent(true))
                }
                onEvent(VenueListEvent.RefreshEvent(false))
            }.collect {
                onEvent(VenueListEvent.EmptyEvent(false))
                originalVenueList.add(it)
                Log.e("CommonVenueViewModel", it.toString())
            }
        }
    }

    fun onEvent(event: VenueListEvent) {
        when (event) {
            is VenueListEvent.RefreshEvent -> {
                if (event.isRefreshEvent) {
                    synchronized(this) {
                        originalVenueList.clear()
                        fetchVenueDetails()
                        _venueListState.value = venueListState.value.copy(
                            listOfVenues = emptyList(),
                            newDataAvailable = false,
                            isEmpty = true,
                            isLoading = true
                        )
                    }
                }
                _venueListState.value = venueListState.value.copy(
                    isLoading = event.isRefreshEvent
                )
            }
            is VenueListEvent.UpdatedSortMode -> {
                if (venueListState.value.sortingMode != event.sortedMode) {
                    _venueListState.value = venueListState.value.copy(
                        sortingMode = event.sortedMode,
                        listOfVenues = emptyList(),
                        isLoading = true,
                        isEmpty = true,
                        newDataAvailable = false
                    )
                    onEvent(VenueListEvent.RefreshEvent(true))
                }
            }
            is VenueListEvent.NewDataEvent -> {
                _venueListState.value = venueListState.value.copy(
                    newDataAvailable = event.available,
                    isEmpty = false,
                    isLoading = false,
                    listOfVenues = originalVenueList
                )
            }
            else -> {}
        }
    }
}