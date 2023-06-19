package com.bookmyshow.feature_two.di

import com.bookmyshow.common_ui.di.CommonDaggerProvider

object FeatureTwoDaggerProvider {
    val component: FeatureTwoComponent by lazy {
        DaggerFeatureTwoComponent.factory().create(
            commonComponent = CommonDaggerProvider.component
        )
    }
}