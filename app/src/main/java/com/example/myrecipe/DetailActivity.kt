package com.example.myrecipe

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.myrecipe.StartActivity.Companion.recipeTypes
import com.example.myrecipe.database.RecipeDatabase
import com.example.myrecipe.entities.Recipes
import kotlinx.android.synthetic.main.activity_add_edit.*
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_detail.imgToolbarBtnBack
import kotlinx.android.synthetic.main.activity_detail.tvIngredients
import kotlinx.android.synthetic.main.activity_detail.tvInstructions
import kotlinx.coroutines.launch

class DetailActivity : BaseActivity() {
    var recipe = ArrayList<Recipes>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val idRecipes = intent.getStringExtra("id")

        getRecipe(idRecipes!!)

        imgToolbarBtnBack.setOnClickListener{
            finish()
        }

        btnEdit.setOnClickListener {
            val intent = Intent(this, AddEditActivity::class.java)
            intent.putExtra("id", idRecipes)
            intent.putExtra("activity", "detail")
            startActivity(intent)
        }

        btnDelete.setOnClickListener {
            launch {
                this.let {
                    RecipeDatabase.getDatabase(this@DetailActivity).recipeDao().deleteRecipe(idRecipes)
                }
            }
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun getRecipe(id: String) {
        launch {
            this.let {
                val recipeFromDatabase = RecipeDatabase.getDatabase(this@DetailActivity).recipeDao().getSpecificRecipe(id)
                recipe = recipeFromDatabase as ArrayList<Recipes>

                tvDishNameDetail.text = recipe[0].recipeName
                tvCategoryDetail.text = recipe[0].categoryName
                Glide.with(this@DetailActivity).load(recipe[0].recipeImage).into(img_dishDetail)
                tvIngredients.text = recipe[0].ingredients
                tvInstructions.text = recipe[0].instructions
            }
        }


    }
}