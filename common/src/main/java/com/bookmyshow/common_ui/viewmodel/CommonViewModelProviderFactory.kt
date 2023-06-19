package com.bookmyshow.common_ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * This is Venue view model factory class which will provides venue view model with required param
 */
class CommonViewModelProviderFactory constructor(val viewModel: CommonVenueViewModel) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return viewModel as T
    }
}