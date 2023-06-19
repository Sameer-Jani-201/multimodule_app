package com.bookmyshow.common_ui.di

import com.bookmyshow.domain_layer.di.DomainDaggerProvider

object CommonDaggerProvider {

    val component: CommonComponent by lazy {
        DaggerCommonComponent.factory().create(
            domainComponent = DomainDaggerProvider.component
        )
    }
}