package com.ewingelen.bmicalculator.calculator.presentation

import androidx.lifecycle.ViewModel
import com.ewingelen.bmicalculator.calculator.domain.CalculateInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CalculatorViewModel @Inject constructor(
    private val calculateInteractor: CalculateInteractor
) : ViewModel() {

    fun calculateBmi(weight: Int, height: Int) =
        calculateInteractor.calculateBmi(weight, height)

    fun calculatePonderalIndex(weight: Int, height: Int) =
        calculateInteractor.calculatePonderalIndex(weight, height)
}