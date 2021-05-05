package com.example.myrecipe.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.*
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myrecipe.R
import com.example.myrecipe.entities.Categories
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.rv_category.view.*
import java.util.*
import kotlin.collections.ArrayList

class CategoryAdapter: RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {
    var listener: OnItemClickListener? = null
    var context: Context? = null
    var arrayCategory = ArrayList<Categories>()
    class CategoryViewHolder(view: View): RecyclerView.ViewHolder(view){

    }

    fun setData(arrayData: List<Categories>){
        arrayCategory = arrayData as ArrayList<Categories>
    }

    fun setClickListener(listener1: OnItemClickListener){
        listener = listener1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        context = parent.context
        return CategoryViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.rv_category,parent,false))
    }

    override fun getItemCount(): Int {
        return arrayCategory.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.itemView.tv_category.text = arrayCategory[position].categoryName
        Glide.with(context!!).load(arrayCategory[position].categoryImage).into(holder.itemView.img_category)
        holder.itemView.rootView.setOnClickListener {
            listener!!.onClicked(arrayCategory[position].categoryName)
        }
    }

    interface OnItemClickListener{
        fun onClicked(categoryName: String)
    }
}