package com.ewingelen.bmicalculator.di

import com.google.android.gms.ads.AdRequest
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun provideAdRequest() = AdRequest.Builder().build()
}
