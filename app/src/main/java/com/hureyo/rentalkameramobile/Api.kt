package com.hureyo.rentalkameramobile

import com.hureyo.rentalkameramobile.models.AlatResponse
import com.hureyo.rentalkameramobile.models.CategoryResponse
import retrofit2.Call
import retrofit2.http.GET

interface Api {
    @GET("alat")
    fun getAlat(): Call<AlatResponse>

    @GET("category")
    fun getCategory(): Call<CategoryResponse>
}