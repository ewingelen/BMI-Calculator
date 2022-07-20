package com.ewingelen.bmicalculator.calculator.domain

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test

class CalculatePonderalIndexUseCaseTest {

    @Test
    fun `test success`() {
        val calculatePonderalIndexUseCase = CalculatePonderalIndexUseCase()
        val actual = calculatePonderalIndexUseCase(70, 185)
        assertEquals(11.06f, actual)
    }

    @Test
    fun `test fail`() {
        val calculatePonderalIndexUseCase = CalculatePonderalIndexUseCase()
        val actual = calculatePonderalIndexUseCase(80, 155)
        assertNotEquals(11.06f, actual)
    }
}