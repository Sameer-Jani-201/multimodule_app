package com.bookmyshow.common_ui.events

import com.bookmyshow.domain_layer.utils.SortedMode

/**
 * This is for all VenueList Events
 */
sealed class VenueListEvent {
    data class RefreshEvent(val isRefreshEvent: Boolean) : VenueListEvent()
    data class UpdatedSortMode(val sortedMode: SortedMode) : VenueListEvent()
    data class NewDataEvent(val available: Boolean) : VenueListEvent()
    data class EmptyEvent(val isEmpty: Boolean) : VenueListEvent()
}
