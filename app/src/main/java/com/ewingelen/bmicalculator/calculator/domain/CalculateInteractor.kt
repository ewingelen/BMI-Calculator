package com.ewingelen.bmicalculator.calculator.domain

data class CalculateInteractor(
    val calculateBmi: CalculateBmiUseCase,
    val calculatePonderalIndex: CalculatePonderalIndexUseCase
)
