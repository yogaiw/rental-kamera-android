package com.hureyo.rentalkameramobile.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hureyo.rentalkameramobile.DetailActivity
import com.hureyo.rentalkameramobile.R
import com.hureyo.rentalkameramobile.helper.toRupiah
import com.hureyo.rentalkameramobile.models.Alat
import kotlinx.android.synthetic.main.item_alat.view.*

class AlatAdapter(private val context: Context, private val list: ArrayList<Alat>): RecyclerView.Adapter<AlatAdapter.AlatViewHolder>() {
    inner class AlatViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(alatResponse: Alat) {
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
        holder.itemView.cv_alat.setOnClickListener {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("ALAT_ID", list[position].id)
            context.startActivity(intent)
        }
    }
}