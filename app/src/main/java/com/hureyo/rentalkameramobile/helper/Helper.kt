package com.hureyo.rentalkameramobile.helper

import java.text.NumberFormat
import java.util.*

fun Int.toRupiah() : String {
    val locale = Locale("id", "ID")
    val formatter = NumberFormat.getCurrencyInstance(locale)
    return formatter.format(this)
}