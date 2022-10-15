package com.hureyo.rentalkameramobile

import com.hureyo.rentalkameramobile.models.AlatResponse
import com.hureyo.rentalkameramobile.models.CategoryResponse
import com.hureyo.rentalkameramobile.models.DetailAlat
import com.hureyo.rentalkameramobile.models.DetailResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {
    @GET("alat")
    fun getAlat(): Call<AlatResponse>

    @GET("alat/{id}")
    fun getDetailAlat(@Path("id") id : Int) : Call<DetailResponse>

    @GET("alat")
    fun getFilteredAlat(@Query("category") category: Int): Call<AlatResponse>

    @GET("category")
    fun getCategory(): Call<CategoryResponse>
}