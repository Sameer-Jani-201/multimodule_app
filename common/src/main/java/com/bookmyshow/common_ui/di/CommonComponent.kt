package com.bookmyshow.common_ui.di

import com.bookmyshow.common_ui.viewmodel.CommonVenueViewModel
import com.bookmyshow.common_ui.viewmodel.CommonViewModelProviderFactory
import com.bookmyshow.core.ImageLoader
import com.bookmyshow.core.di.CoreModule
import com.bookmyshow.domain_layer.di.DomainComponent
import dagger.Component

@Component(
    dependencies = [DomainComponent::class],
    modules = [CommonModule::class, CoreModule::class]
)
interface CommonComponent {

    @Component.Factory
    interface Factory {
        fun create(
            domainComponent: DomainComponent
        ): CommonComponent
    }

    fun provideImageLoader(): ImageLoader

    fun provideCommonVenueViewModel(): CommonVenueViewModel

    fun provideCommonVenueViewModelFactory(): CommonViewModelProviderFactory
}