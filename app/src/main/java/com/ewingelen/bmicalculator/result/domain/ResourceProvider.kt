package com.ewingelen.bmicalculator.result.domain

import android.content.Context
import androidx.annotation.StringRes

interface ResourceProvider {

    fun provideString(@StringRes id: Int): String

    class Base(private val context: Context): ResourceProvider {

        override fun provideString(id: Int) = context.getString(id)
    }
}