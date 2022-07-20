package com.ewingelen.bmicalculator.di

import android.content.Context
import com.ewingelen.bmicalculator.result.data.ResultRepositoryImpl
import com.ewingelen.bmicalculator.result.domain.*
import com.google.android.gms.ads.AdLoader
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class ResultModule {

    private companion object {
        const val ADD_UNIT_ID = "ca-app-pub-3940256099942544/2247696110"
    }

    @Provides
    fun provideAdLoader(@ApplicationContext context: Context): AdLoader.Builder =
        AdLoader.Builder(context, ADD_UNIT_ID)

    @Provides
    fun provideResourceProvider(@ApplicationContext context: Context): ResourceProvider =
        ResourceProvider.Base(context)

    @Provides
    fun provideResultRepository(@ApplicationContext context: Context): ResultRepository =
        ResultRepositoryImpl(context)

    @Provides
    fun provideResultInteractor(resourceProvider: ResourceProvider, repository: ResultRepository) =
        ResultInteractor(
            resultUseCase = ResultUseCase(resourceProvider),
            saveBitmapUseCase = SaveBitmapUseCase(repository),
            loadBitmapUseCase = LoadBitmapUseCase(repository)
        )
}