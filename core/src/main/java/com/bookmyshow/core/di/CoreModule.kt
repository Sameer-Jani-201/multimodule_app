package com.bookmyshow.core.di

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class CoreModule {

    @Provides
    fun provideContext(): Context {
        return CoreComponentProvider.coreComponent.getContext()
    }
}