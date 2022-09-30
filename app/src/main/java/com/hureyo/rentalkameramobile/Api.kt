package com.hureyo.rentalkameramobile

import retrofit2.Call
import retrofit2.http.GET

interface Api {
    @GET("api/v1/alat")

    fun getAlat(): Call<ArrayList<AlatResponse>>
}