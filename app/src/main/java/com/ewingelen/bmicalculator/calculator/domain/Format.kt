package com.ewingelen.bmicalculator.calculator.domain

import java.text.DecimalFormat

class Format {

    fun twoAfterZero(num: Float) = DecimalFormat("#.##")
        .format(num).replace(",", ".").toFloat()
}