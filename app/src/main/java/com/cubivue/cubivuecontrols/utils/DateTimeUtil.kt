package com.cubivue.cubivuecontrols.utils

import java.text.SimpleDateFormat
import java.util.*

enum class DateTimeFormats(val value: String) {
    FULL_DATE_TIME("yyyy-MM-dd HH:mm:ss"),
}

fun getReadableTime(timestamp: Long, format: DateTimeFormats = DateTimeFormats.FULL_DATE_TIME): String {
    return if (timestamp != 0L) {
        val date1 = Date(timestamp)
        val outFormat = SimpleDateFormat(
                format.value,
                Locale.ENGLISH
        )
        outFormat.format(date1)
    } else "No Time Provided!"
}