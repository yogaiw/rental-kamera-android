package com.hureyo.rentalkameramobile.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hureyo.rentalkameramobile.R
import com.hureyo.rentalkameramobile.helper.toHumenDate
import com.hureyo.rentalkameramobile.models.Booked
import kotlinx.android.synthetic.main.item_booked.view.*

class BookedListAdapter(private val context: Context, private val list: ArrayList<Booked>) : RecyclerView.Adapter<BookedListAdapter.BookedListViewHolder>() {
    inner class BookedListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @SuppressLint("SetTextI18n")
        fun bind(booked : Booked) {
            with(itemView) {
                tv_duration.text = booked.durasi.toString() + " Jam"
                tv_start.text = booked.start.toHumenDate()
                tv_end.text = booked.end.toHumenDate()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookedListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_booked, parent, false)
        return BookedListViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookedListViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size
}