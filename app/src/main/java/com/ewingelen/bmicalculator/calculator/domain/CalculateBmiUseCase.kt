package com.ewingelen.bmicalculator.calculator.domain

import kotlin.math.pow

class CalculateBmiUseCase(private val format: Format) {

    operator fun invoke(weight: Int, height: Int) = format.twoAfterZero(
        weight.toFloat() / (height.toFloat() / 100).pow(2)
    )
}

