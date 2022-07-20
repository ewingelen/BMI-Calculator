package com.ewingelen.bmicalculator.result.domain

data class ResultInteractor(
    val resultUseCase: ResultUseCase,
    val saveBitmapUseCase: SaveBitmapUseCase,
    val loadBitmapUseCase: LoadBitmapUseCase
)