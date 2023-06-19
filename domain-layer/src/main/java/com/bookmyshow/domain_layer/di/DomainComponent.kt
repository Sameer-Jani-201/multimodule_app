package com.bookmyshow.domain_layer.di

import com.bookmyshow.data_layer.di.DataComponent
import com.bookmyshow.domain_layer.usecase.FetchDefaultVenueUsecase
import dagger.Component

@Component(
    dependencies = [DataComponent::class]
)
interface DomainComponent {
    @Component.Factory
    interface Factory {
        fun create(
            dataComponent: DataComponent
        ): DomainComponent
    }

    fun provideCommonUsecase(): FetchDefaultVenueUsecase
}