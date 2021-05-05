package com.example.myrecipe

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myrecipe.adapter.CategoryAdapter
import com.example.myrecipe.adapter.RecipeAdapter
import com.example.myrecipe.database.RecipeDatabase
import com.example.myrecipe.entities.Categories
import com.example.myrecipe.entities.Recipes
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.rv_category.*
import kotlinx.coroutines.launch

class MainActivity : BaseActivity() {
    var arrayCategory = ArrayList<Categories>()
    var arrayRecipe = ArrayList<Recipes>()

    var categoryAdapter = CategoryAdapter()
    var recipeAdapter = RecipeAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getDataFromDatabase()

        categoryAdapter.setClickListener(onCLickedCategory)
        recipeAdapter.setClickListener(onCLickedRecipe)

        btnAdd.setOnClickListener {
            val intent = Intent(this, AddEditActivity::class.java)
            intent.putExtra("activity", "main")
            startActivity(intent)
        }
    }

    private val onCLickedCategory  = object : CategoryAdapter.OnItemClickListener{
        override fun onClicked(categoryName: String) {
            getRecipeDataFromDatabase(categoryName)
        }
    }

    private val onCLickedRecipe  = object : RecipeAdapter.OnItemClickListener{
        override fun onClicked(id: String) {
            val intent = Intent(this@MainActivity,DetailActivity::class.java)
            intent.putExtra("id",id)
            startActivity(intent)
        }
    }

    private fun getDataFromDatabase() {
        launch {
            this.let {
                val category = RecipeDatabase.getDatabase(this@MainActivity).recipeDao().getAllCategories()
                arrayCategory = category as ArrayList<Categories>
                arrayCategory.reverse()

                getRecipeDataFromDatabase(arrayCategory[0].categoryName)
                categoryAdapter.setData(arrayCategory)
                rv_category.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
                rv_category.adapter = categoryAdapter
            }
        }
    }

    private fun getRecipeDataFromDatabase(categoryName: String) {
        tv_categoryMain.text = "$categoryName Category"
        launch {
            this.let {
                val recipe = RecipeDatabase.getDatabase(this@MainActivity).recipeDao().getSpecificRecipeCategory(categoryName)
                arrayRecipe = recipe as ArrayList<Recipes>
                recipeAdapter.setData(arrayRecipe)
                rv_dish.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
                rv_dish.adapter = recipeAdapter
            }
        }
    }
}