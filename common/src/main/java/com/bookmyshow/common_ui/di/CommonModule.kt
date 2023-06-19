package com.bookmyshow.common_ui.di

import android.content.Context
import com.bookmyshow.common_ui.imageloader.ImageLoaderImpl
import com.bookmyshow.common_ui.viewmodel.CommonVenueViewModel
import com.bookmyshow.common_ui.viewmodel.CommonViewModelProviderFactory
import com.bookmyshow.core.ImageLoader
import com.bookmyshow.domain_layer.usecase.FetchDefaultVenueUsecase
import dagger.Module
import dagger.Provides

@Module
class CommonModule {
    @Provides
    fun getImageLoader(
        context: Context
    ): ImageLoader {
        return ImageLoaderImpl(
            context = context
        )
    }

    @Provides
    fun provideViewModel(fetchDefaultVenueUsecase: FetchDefaultVenueUsecase): CommonVenueViewModel {
        return CommonVenueViewModel(fetchDefaultVenueUsecase)
    }

    @Provides
    fun provideViewModelFactory(viewModel: CommonVenueViewModel): CommonViewModelProviderFactory {
        return CommonViewModelProviderFactory(viewModel)
    }
}