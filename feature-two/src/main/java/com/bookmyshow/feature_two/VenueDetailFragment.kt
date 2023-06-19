package com.bookmyshow.feature_two

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bookmyshow.common_ui.state.FeatureTwoUiState
import com.bookmyshow.common_ui.viewmodel.CommonVenueViewModel
import com.bookmyshow.common_ui.viewmodel.CommonViewModelProviderFactory
import com.bookmyshow.core.ImageLoader
import com.bookmyshow.feature_two.databinding.FragmentTwoBinding
import com.bookmyshow.feature_two.di.FeatureTwoDaggerProvider
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

/**
 * This is a [VenueDetailFragment] for display the venue details.
 */
class VenueDetailFragment : Fragment() {

    @Inject
    lateinit var viewModel: CommonVenueViewModel

    @Inject
    lateinit var viewModelFactory: CommonViewModelProviderFactory

    @Inject
    lateinit var imageLoader: ImageLoader

    private lateinit var selectedVenueState: StateFlow<FeatureTwoUiState>
    private lateinit var binding: FragmentTwoBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        FeatureTwoDaggerProvider.component.inject(this)
        Log.e("VenueDetailFragment", "ViewModel : " + viewModel)
        Log.e("VenueDetailFragment", "ViewModelFactory : " + viewModelFactory)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTwoBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(
            requireActivity(), viewModelFactory
        )[CommonVenueViewModel::class.java]
        selectedVenueState = viewModel.selectedVenueState
        binding.venue = selectedVenueState.value.selectedVenue!!
        binding.url =
            "https://static.businessworld.in/article/article_extra_large_image/1609147522_O1aw88_BMS.jpg"
        binding.imageLoader = imageLoader
    }
}