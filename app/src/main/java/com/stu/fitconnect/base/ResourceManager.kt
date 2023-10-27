package com.stu.fitconnect.base

import android.content.Context
import com.stu.fitconnect.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


class ResourceManager @Inject constructor(
    @ApplicationContext private val context: Context
) {

    fun getRawJsonAsString(resourceId: Int): String {
        val inputStream = context.resources.openRawResource(resourceId)
        return inputStream.bufferedReader().use { it.readText() }
    }
}