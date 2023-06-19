package com.bookmyshow.feature_two.di

import com.bookmyshow.common_ui.di.CommonComponent
import com.bookmyshow.core.di.CoreModule
import com.bookmyshow.feature_two.VenueDetailFragment
import com.bookmyshow.network.di.NetworkModule
import dagger.Component

@Component(
    dependencies = [CommonComponent::class],
    modules = [NetworkModule::class, CoreModule::class]
)
interface FeatureTwoComponent {

    @Component.Factory
    interface Factory {
        fun create(
            commonComponent: CommonComponent
        ): FeatureTwoComponent
    }

    fun inject(fragment: VenueDetailFragment)
}