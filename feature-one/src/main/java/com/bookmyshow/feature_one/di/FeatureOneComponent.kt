package com.bookmyshow.feature_one.di

import com.bookmyshow.common_ui.di.CommonComponent
import com.bookmyshow.feature_one.VenueListFragment
import dagger.Component

@Component(
    dependencies = [CommonComponent::class]
)
interface FeatureOneComponent {

    @Component.Factory
    interface Factory {
        fun create(
            commonComponent: CommonComponent
        ): FeatureOneComponent
    }

    fun inject(venueListFragment: VenueListFragment)
}