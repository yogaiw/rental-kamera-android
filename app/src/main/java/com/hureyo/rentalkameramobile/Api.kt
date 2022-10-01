package com.hureyo.rentalkameramobile

import com.hureyo.rentalkameramobile.models.AlatResponse
import retrofit2.Call
import retrofit2.http.GET

interface Api {
    @GET("alat")
    fun getAlat(): Call<ArrayList<AlatResponse>>
}