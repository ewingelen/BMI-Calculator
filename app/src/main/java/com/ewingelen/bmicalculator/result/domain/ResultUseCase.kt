package com.ewingelen.bmicalculator.result.domain

import com.ewingelen.bmicalculator.R

class ResultUseCase(private val resourceProvider: ResourceProvider) {

    private companion object {
        const val MIN_NORMAL_BMI = 18.5
        const val MAX_NORMAL_BMI = 25
    }

    operator fun invoke(bmi: Float) =
        resourceProvider.provideString(
            when {
                bmi < MIN_NORMAL_BMI -> R.string.label_underweight
                bmi < MAX_NORMAL_BMI -> R.string.label_normal
                else -> R.string.label_overweight
            }
        )
}