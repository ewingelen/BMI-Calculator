package com.ewingelen.bmicalculator.result.domain

import android.graphics.Bitmap
import java.io.File

interface ResultRepository {

    fun saveBitmap(bmp: Bitmap)

    fun loadBitmap(): File
}