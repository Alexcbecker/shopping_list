package com.presentation.extension

import java.text.SimpleDateFormat
import java.util.*

val LOCALE_BRAZIL = Locale("pt", "BR")

fun Date.toBrazilString(): String {
    val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy", LOCALE_BRAZIL)
    simpleDateFormat.timeZone = TimeZone.getTimeZone("GMT-3")
    return simpleDateFormat.format(this)
}