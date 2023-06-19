package com.bookmyshow.domain_layer.di

import com.bookmyshow.data_layer.di.DataDaggerProvider

object DomainDaggerProvider {
    val component: DomainComponent by lazy {
        DaggerDomainComponent.factory().create(
            dataComponent = DataDaggerProvider.component
        )
    }
}