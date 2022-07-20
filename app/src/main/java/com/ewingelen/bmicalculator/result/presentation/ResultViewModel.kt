package com.ewingelen.bmicalculator.result.presentation

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import com.ewingelen.bmicalculator.result.domain.ResultInteractor
import com.ewingelen.bmicalculator.result.domain.ResultRepository
import com.ewingelen.bmicalculator.result.domain.ResultUseCase
import com.ewingelen.bmicalculator.result.domain.SaveBitmapUseCase
import com.google.android.gms.ads.AdLoader
import com.google.android.gms.ads.AdRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ResultViewModel @Inject constructor(
    private val resultInteractor: ResultInteractor
) : ViewModel() {

    fun result(bmi: Float) = resultInteractor.resultUseCase(bmi)

    fun saveBitmap(bmp: Bitmap) = resultInteractor.saveBitmapUseCase(bmp)

    fun loadBitmap() = resultInteractor.loadBitmapUseCase()
}