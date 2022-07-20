package com.ewingelen.bmicalculator.result.domain

class LoadBitmapUseCase(private val repository: ResultRepository) {

    operator fun invoke() = repository.loadBitmap()
}