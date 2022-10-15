package com.hureyo.rentalkameramobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.hureyo.rentalkameramobile.adapters.BookedListAdapter
import com.hureyo.rentalkameramobile.helper.toRupiah
import com.hureyo.rentalkameramobile.models.Booked
import com.hureyo.rentalkameramobile.models.DetailResponse
import kotlinx.android.synthetic.main.activity_detail.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailActivity : AppCompatActivity() {

    private val bookedList = ArrayList<Booked>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val idAlat = intent.extras?.get("ALAT_ID") as Int
        rv_booked.setHasFixedSize(true)
        rv_booked.layoutManager = LinearLayoutManager(this)

        pb_detail.visibility + View.VISIBLE

        RetrofitClient.instance.getDetailAlat(idAlat).enqueue(object : Callback<DetailResponse> {
            override fun onResponse(
                call: Call<DetailResponse>,
                response: Response<DetailResponse>
            ) {
                val detail = response.body()?.data
                val booked = response.body()?.booked

                tv_detail_nama_alat.text = detail?.nama_alat
                tv_harga24_detail.text = detail?.harga24?.toRupiah()
                tv_harga12_detail.text = detail?.harga12?.toRupiah()
                tv_harga6_detail.text = detail?.harga6?.toRupiah()

                booked?.let { bookedList.addAll(it) }

                val adapter = BookedListAdapter(this@DetailActivity, bookedList)
                rv_booked.adapter = adapter
                pb_detail.visibility = View.GONE
            }

            override fun onFailure(call: Call<DetailResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

    override fun onResume() {
        pb_detail.visibility + View.VISIBLE
        super.onResume()
    }

    override fun onPause() {
        pb_detail.visibility = View.GONE
        super.onPause()
    }
}