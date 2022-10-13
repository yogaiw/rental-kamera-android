package com.hureyo.rentalkameramobile.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.hureyo.rentalkameramobile.R
import com.hureyo.rentalkameramobile.RetrofitClient
import com.hureyo.rentalkameramobile.models.Alat
import com.hureyo.rentalkameramobile.models.AlatResponse
import com.hureyo.rentalkameramobile.models.Category
import kotlinx.android.synthetic.main.item_category.view.*
import retrofit2.Call
import retrofit2.Response

class CategoryAdapter(private val list: ArrayList<Category>) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {
    val listAlat = ArrayList<Alat>()
    inner class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(categoryResponse: Category) {
            with(itemView) {
                btn_category.text = categoryResponse.nama_kategori
                btn_category.setOnClickListener {
                    RetrofitClient.instance.getFilteredAlat(categoryResponse.id).enqueue(object : retrofit2.Callback<AlatResponse> {
                        @SuppressLint("NotifyDataSetChanged")
                        override fun onResponse(
                            call: Call<AlatResponse>,
                            response: Response<AlatResponse>
                        ) {
                            val listResponse = response.body()?.data
                            listAlat.clear()
                            listResponse?.let {
                                listAlat.addAll(it)
                            }
                            val adapter = AlatAdapter(listAlat)
                            adapter.notifyDataSetChanged()
                            Toast.makeText(context, listResponse.toString() ,Toast.LENGTH_SHORT).show()
                        }

                        override fun onFailure(call: Call<AlatResponse>, t: Throwable) {

                        }

                    })
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size
}