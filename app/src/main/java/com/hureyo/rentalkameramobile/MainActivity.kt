package com.hureyo.rentalkameramobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.hureyo.rentalkameramobile.adapters.AlatAdapter
import com.hureyo.rentalkameramobile.models.AlatResponse
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val list = ArrayList<AlatResponse>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rv_alat.setHasFixedSize(true)
        rv_alat.layoutManager = LinearLayoutManager(this)

        RetrofitClient.instance.getAlat().enqueue(object : Callback<ArrayList<AlatResponse>>{
            override fun onResponse(
                call: Call<ArrayList<AlatResponse>>,
                response: Response<ArrayList<AlatResponse>>
            ) {
                response.body()?.let { list.addAll(it) }
                val adapter = AlatAdapter(list)
                rv_alat.adapter = adapter

                shimmer_view_container.stopShimmer()
                shimmer_view_container.visibility = View.GONE
            }

            override fun onFailure(call: Call<ArrayList<AlatResponse>>, t: Throwable) {

            }

        })
    }

    override fun onResume() {
        shimmer_view_container.startShimmer()
        super.onResume()
    }

    override fun onPause() {
        shimmer_view_container.stopShimmer()
        super.onPause()
    }
}