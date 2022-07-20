package com.ewingelen.bmicalculator.result.data

import android.content.Context
import android.graphics.Bitmap
import com.ewingelen.bmicalculator.result.domain.ResultRepository
import java.io.File
import java.io.IOException

class ResultRepositoryImpl(private val context: Context): ResultRepository {

    private companion object {
        const val FILE_NAME = "bmi_result.jpg"
    }

    override fun saveBitmap(bmp: Bitmap) {
        try {
            context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE).use { stream ->
                if (!bmp.compress(Bitmap.CompressFormat.JPEG, 95, stream))
                    throw IOException()
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    override fun loadBitmap(): File {
        val path = context.filesDir.absolutePath + File.separator + FILE_NAME
        return File(path)
    }
}