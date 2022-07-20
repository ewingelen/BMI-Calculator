package com.ewingelen.bmicalculator.di

import com.ewingelen.bmicalculator.calculator.domain.CalculateBmiUseCase
import com.ewingelen.bmicalculator.calculator.domain.CalculateInteractor
import com.ewingelen.bmicalculator.calculator.domain.CalculatePonderalIndexUseCase
import com.ewingelen.bmicalculator.calculator.domain.Format
import com.google.android.gms.ads.AdRequest
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class CalculateModule {

    @Provides
    fun provideFormat() = Format()

    @Provides
    fun provideCalculateInteractor(format: Format) = CalculateInteractor(
        calculateBmi = CalculateBmiUseCase(format),
        calculatePonderalIndex = CalculatePonderalIndexUseCase(format)
    )
}