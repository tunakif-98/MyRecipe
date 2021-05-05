package com.example.myrecipe.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myrecipe.R
import com.example.myrecipe.entities.Recipes
import kotlinx.android.synthetic.main.rv_dishes.view.*

class RecipeAdapter: RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {
    var listener: OnItemClickListener? = null
    var context: Context? = null
    var arrayRecipe = ArrayList<Recipes>()

    class RecipeViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    }

    fun setData(arrayData: List<Recipes>) {
        arrayRecipe = arrayData as ArrayList<Recipes>
    }

    fun setClickListener(listener1: OnItemClickListener) {
        listener = listener1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        context = parent.context
        return RecipeViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.rv_dishes, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return arrayRecipe.size
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.itemView.tv_dish_name.text = arrayRecipe[position].recipeName
        Glide.with(context!!).load(arrayRecipe[position].recipeImage).into(holder.itemView.img_dish)
        holder.itemView.rootView.setOnClickListener {
            listener!!.onClicked(arrayRecipe[position].idRecipes.toString())
        }
    }

    interface OnItemClickListener {
        fun onClicked(id: String){
            Log.d("clicked", id)
        }
    }
}