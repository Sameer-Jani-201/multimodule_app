package com.bookmyshow.feature_one

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.bookmyshow.common_ui.events.VenueListEvent
import com.bookmyshow.common_ui.state.FeatureOneUiState
import com.bookmyshow.common_ui.viewmodel.CommonVenueViewModel
import com.bookmyshow.common_ui.viewmodel.CommonViewModelProviderFactory
import com.bookmyshow.core.ImageLoader
import com.bookmyshow.domain_layer.model.VenuesModel
import com.bookmyshow.feature_one.adapter.VenueAdapter
import com.bookmyshow.feature_one.databinding.FragmentVenueBinding
import com.bookmyshow.feature_one.di.FeatureOneDaggerProvider
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

/**
 * This is a Venue Fragment where all venue of program displays.
 */
class VenueListFragment : Fragment() {
    //https://static.businessworld.in/article/article_extra_large_image/1609147522_O1aw88_BMS.jpg
    @Inject
    lateinit var viewModel: CommonVenueViewModel

    @Inject
    lateinit var imageLoader: ImageLoader

    @Inject
    lateinit var viewModelFactory: CommonViewModelProviderFactory

    private lateinit var venueListUiState: StateFlow<FeatureOneUiState>
    private val listOfVenues = java.util.ArrayList<VenuesModel>()
    lateinit var binding: FragmentVenueBinding
    private lateinit var adapter: VenueAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        FeatureOneDaggerProvider.component.inject(this)
        Log.e("VenueListFragment", "ViewModel : " + viewModel)
        Log.e("VenueListFragment", "ViewModelFactory : " + viewModelFactory)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentVenueBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(
            requireActivity(), viewModelFactory
        )[CommonVenueViewModel::class.java]
        venueListUiState = viewModel.venueListState
        binding.rvVenues.layoutManager = LinearLayoutManager(this@VenueListFragment.context)

        adapter = VenueAdapter(listOfVenues, imageLoader)
        adapter.registerClicks(itemClick)
        binding.rvVenues.adapter = adapter
        updateUi()
    }

    private val itemClick = object : VenueAdapter.IClick {
        override fun onClick(id: Int) {
            viewModel.itemClick(id)
            VenueListFragmentDirections.actionVenueFragmentToNavVenueDetailGraph().apply {
                findNavController().navigate(this)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.onEvent(VenueListEvent.RefreshEvent(true))
    }

    private fun updateUi() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                venueListUiState.collect { state ->
                    if (state.isEmpty) {
                        listOfVenues.clear()
                    } else if (state.isLoading) {

                    } else if (state.newDataAvailable) {
                        listOfVenues.clear()
                        listOfVenues.addAll(state.listOfVenues)
                        adapter?.notifyDataSetChanged()
                    }
                }
            }
        }
    }
}