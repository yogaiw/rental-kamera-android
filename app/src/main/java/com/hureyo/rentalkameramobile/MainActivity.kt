package com.hureyo.rentalkameramobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.hureyo.rentalkameramobile.adapters.AlatAdapter
import com.hureyo.rentalkameramobile.adapters.CategoryAdapter
import com.hureyo.rentalkameramobile.listener.CategoryClickListener
import com.hureyo.rentalkameramobile.models.Alat
import com.hureyo.rentalkameramobile.models.AlatResponse
import com.hureyo.rentalkameramobile.models.Category
import com.hureyo.rentalkameramobile.models.CategoryResponse
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), CategoryClickListener {

    private val list = ArrayList<Alat>()
    private val listCategory = ArrayList<Category>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rv_alat.setHasFixedSize(true)
        rv_alat.layoutManager = GridLayoutManager(this, 2)

        rv_category.setHasFixedSize(true)
        rv_category.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        getAlat()

        RetrofitClient.instance.getCategory().enqueue(object : Callback<CategoryResponse> {
            override fun onResponse(
                call: Call<CategoryResponse>,
                response: Response<CategoryResponse>
            ) {
                val categoryResponse = response.body()?.data
                categoryResponse?.let { listCategory.addAll(it) }

                val adapter = CategoryAdapter(this@MainActivity, listCategory, this@MainActivity)
                rv_category.adapter = adapter

                shimmer_category.stopShimmer()
                shimmer_category.visibility = View.GONE
            }

            override fun onFailure(call: Call<CategoryResponse>, t: Throwable) {

            }

        })
    }

    override fun onCategoryClickListener(data: Category) {
        shimmer_view_container.startShimmer()
        RetrofitClient.instance.getFilteredAlat(data.id).enqueue(object : Callback<AlatResponse> {
            override fun onResponse(call: Call<AlatResponse>, response: Response<AlatResponse>) {
                val listResponse = response.body()?.data
                list.clear()
                listResponse?.let { list.addAll(it) }

                val adapter = AlatAdapter(list)
                rv_alat.adapter = adapter

                adapter.notifyDataSetChanged()

                shimmer_view_container.stopShimmer()
                shimmer_view_container.visibility = View.GONE
            }

            override fun onFailure(call: Call<AlatResponse>, t: Throwable) {

            }
        })
    }

    private fun getAlat() {
        RetrofitClient.instance.getAlat().enqueue(object : Callback<AlatResponse>{
            override fun onResponse(call: Call<AlatResponse>, response: Response<AlatResponse>) {
                val listResponse = response.body()?.data
                listResponse?.let { list.addAll(it) }

                val adapter = AlatAdapter(list)
                rv_alat.adapter = adapter

                shimmer_view_container.stopShimmer()
                shimmer_view_container.visibility = View.GONE
            }

            override fun onFailure(call: Call<AlatResponse>, t: Throwable) {

            }

        })
    }

    override fun onResume() {
        shimmer_view_container.startShimmer()
        shimmer_category.startShimmer()
        super.onResume()
    }

    override fun onPause() {
        shimmer_view_container.stopShimmer()
        shimmer_category.stopShimmer()
        super.onPause()
    }
}