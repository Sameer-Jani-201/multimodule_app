package com.bookmyshow.common_ui.state

import com.bookmyshow.domain_layer.model.VenuesModel
import com.bookmyshow.domain_layer.utils.SortedMode

/**
 * This is FeatureOne UI States
 */
data class FeatureOneUiState(
    var listOfVenues: List<VenuesModel> = listOf(),
    var isLoading: Boolean = false,
    var isEmpty: Boolean = true,
    var newDataAvailable: Boolean = false,
    var sortingMode: SortedMode = SortedMode.NAME
)
