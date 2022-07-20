package com.ewingelen.bmicalculator.result.domain

import android.graphics.Bitmap

class SaveBitmapUseCase(private val repository: ResultRepository) {

    operator fun invoke(bmp: Bitmap) = repository.saveBitmap(bmp)
}