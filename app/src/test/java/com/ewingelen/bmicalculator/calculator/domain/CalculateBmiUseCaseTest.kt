package com.ewingelen.bmicalculator.calculator.domain

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test

class CalculateBmiUseCaseTest {

    @Test
    fun `test success`() {
        val calculateBmiUseCase = CalculateBmiUseCase()
        val actual = calculateBmiUseCase(70, 185)
        assertEquals(20.45f, actual)
    }

    @Test
    fun `test fail`() {
        val calculateBmiUseCase = CalculateBmiUseCase()
        val actual = calculateBmiUseCase(80, 155)
        assertNotEquals(20.45f, actual)
    }
}