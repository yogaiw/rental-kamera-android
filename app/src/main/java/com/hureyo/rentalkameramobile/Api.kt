package com.hureyo.rentalkameramobile

import com.hureyo.rentalkameramobile.models.AlatResponse
import com.hureyo.rentalkameramobile.models.CategoryResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("alat")
    fun getAlat(): Call<AlatResponse>

    @GET("alat")
    fun getFilteredAlat(@Query("category") category: Int): Call<AlatResponse>

    @GET("category")
    fun getCategory(): Call<CategoryResponse>
}