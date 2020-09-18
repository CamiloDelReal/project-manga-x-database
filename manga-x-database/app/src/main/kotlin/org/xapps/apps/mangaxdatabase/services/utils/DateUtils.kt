package org.xapps.apps.mangaxdatabase.services.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*


object DateUtils {

    @SuppressLint("SimpleDateFormat")
    @JvmStatic
    fun timestamp(format: String = "yyyyMMddHHmmss"): String {
        val formatter = SimpleDateFormat(format)
        val date = Date(System.currentTimeMillis())
        return formatter.format(date)
    }

}