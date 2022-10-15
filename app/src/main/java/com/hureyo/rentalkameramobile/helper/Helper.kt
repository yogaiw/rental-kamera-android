package com.hureyo.rentalkameramobile.helper

import android.annotation.SuppressLint
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

fun Int.toRupiah() : String {
    val locale = Locale("id", "ID")
    val formatter = NumberFormat.getCurrencyInstance(locale)
    return formatter.format(this)
}

@SuppressLint("SimpleDateFormat")
fun String.toHumenDate() : String {
    var parser = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    var formatter = SimpleDateFormat("E, dd MMMM yyyy HH:mm")
    return formatter.format(parser.parse(this))
}