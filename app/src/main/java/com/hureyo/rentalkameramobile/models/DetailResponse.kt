package com.hureyo.rentalkameramobile.models

data class DetailResponse(
    var data: DetailAlat,
    val booked: ArrayList<Booked>
)
