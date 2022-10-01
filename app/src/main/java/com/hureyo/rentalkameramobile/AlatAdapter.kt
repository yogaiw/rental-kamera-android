package com.hureyo.rentalkameramobile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hureyo.rentalkameramobile.helper.toRupiah
import kotlinx.android.synthetic.main.item_alat.view.*

class AlatAdapter (private val list: ArrayList<AlatResponse>): RecyclerView.Adapter<AlatAdapter.AlatViewHolder>() {
    inner class AlatViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(alatResponse: AlatResponse) {
            with(itemView) {
                tv_nama_alat.text = alatResponse.nama_alat
                tv_kategori.text = alatResponse.nama_kategori
                tv_harga24.text = alatResponse.harga24.toRupiah()
                tv_harga12.text = alatResponse.harga12.toRupiah()
                tv_harga6.text = alatResponse.harga6.toRupiah()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlatViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_alat, parent, false)
        return AlatViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: AlatViewHolder, position: Int) {
        holder.bind(list[position])
    }
}