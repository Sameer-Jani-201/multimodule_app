package com.bookmyshow.data_layer.di

object DataDaggerProvider {
    val component: DataComponent by lazy {
        DaggerDataComponent.factory().create()
    }
}