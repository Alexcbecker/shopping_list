package com.presentation.extension

import java.math.BigInteger
import java.text.NumberFormat

fun BigInteger?.toBrazilianRealString(): String {
    if (this == null) NumberFormat.getCurrencyInstance(LOCALE_BRAZIL).format(BigInteger.ZERO)
    return NumberFormat.getCurrencyInstance(LOCALE_BRAZIL).format(this)
}