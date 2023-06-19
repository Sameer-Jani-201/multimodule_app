package com.bookmyshow.data_layer.di

import com.bookmyshow.core.di.CoreModule
import com.bookmyshow.data_layer.repository.ShowTimeApiImpl
import com.bookmyshow.network.di.NetworkModule
import dagger.Component

@Component(
    modules = [NetworkModule::class, CoreModule::class]
)
interface DataComponent {

    @Component.Factory
    interface Factory {
        fun create(
        ): DataComponent
    }

    fun provideShowTimeApi(): ShowTimeApiImpl
}