package com.hureyo.rentalkameramobile.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hureyo.rentalkameramobile.R
import com.hureyo.rentalkameramobile.listener.CategoryClickListener
import com.hureyo.rentalkameramobile.models.Category
import kotlinx.android.synthetic.main.item_category.view.*


class CategoryAdapter(private val context: Context, private val list: ArrayList<Category>, private val categoryClickListener: CategoryClickListener) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {
    inner class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(categoryResponse: Category) {
            with(itemView) {
                btn_category.text = categoryResponse.nama_kategori
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(list[position])
        holder.itemView.btn_category.setOnClickListener {
            categoryClickListener.onCategoryClickListener(list[position])
        }

    }

    override fun getItemCount(): Int = list.size
}