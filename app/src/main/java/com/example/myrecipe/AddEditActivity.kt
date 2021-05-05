package com.example.myrecipe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.bumptech.glide.Glide
import com.example.myrecipe.database.RecipeDatabase
import com.example.myrecipe.entities.Recipes
import kotlinx.android.synthetic.main.activity_add_edit.*
import kotlinx.android.synthetic.main.activity_add_edit.tvIngredients
import kotlinx.android.synthetic.main.activity_add_edit.tvInstructions
import kotlinx.coroutines.launch

class AddEditActivity : BaseActivity() {
    var recipeCategory: Array<String>? = StartActivity.recipeTypes.toTypedArray()
    var recipe = ArrayList<Recipes>()
    private var idRecipe = String()
    private lateinit var spinnerArrayAdapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_edit)

        idRecipe = intent.getStringExtra("id").toString()

        spinnerArrayAdapter = ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, recipeCategory!!)
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerCategory.adapter = spinnerArrayAdapter

        if (intent.getStringExtra("activity").toString() == "detail") {
            getRecipe(idRecipe)
        }

        imgToolbarBtnBack.setOnClickListener {
            finish()
        }

        btnSave.setOnClickListener {
            when {
                intent.getStringExtra("activity").toString() == "detail" -> {
                    updateRecipeInDatabase()
                }
                intent.getStringExtra("activity").toString() == "main" -> {
                    insertNewRecipeIntoDatabase()
                }
            }

            val intent = Intent(this, MainActivity::class.java)
            //intent.putExtra("id", idRecipe)
            startActivity(intent)
        }
    }

    private fun getRecipe(id: String) {
        launch {
            this.let {
                val recipeFromDatabase = RecipeDatabase.getDatabase(this@AddEditActivity).recipeDao().getSpecificRecipe(id)
                recipe = recipeFromDatabase as ArrayList<Recipes>
                var selection = String()

                tvDishName.setText(recipe[0].recipeName)

                for (i in StartActivity.recipeTypes.indices) {
                    if (StartActivity.recipeTypes[i] == recipe[0].categoryName) {
                        selection = i.toString()
                    }
                }

                spinnerCategory.setSelection(selection.toInt())

                Glide.with(this@AddEditActivity).load(recipe[0].recipeImage).into(img_item)
                tvIngredients.setText(recipe[0].ingredients)
                tvInstructions.setText(recipe[0].instructions)
                tvImageURL.setText(recipe[0].recipeImage)
            }
        }


    }

    private fun insertNewRecipeIntoDatabase() {
        launch {
            this.let {
                val recipes = RecipeDatabase.getDatabase(this@AddEditActivity).recipeDao().getAllRecipe()

                val newRecipe = Recipes(
                    idRecipes = recipes.lastIndex + 1,
                    categoryName = spinnerCategory.selectedItem.toString(),
                    recipeName = tvDishName.text.toString(),
                    ingredients = tvIngredients.text.toString(),
                    instructions = tvInstructions.text.toString(),
                    recipeImage = tvImageURL.text.toString()
                )

                RecipeDatabase.getDatabase(this@AddEditActivity).recipeDao().insertRecipe(newRecipe)
            }
        }
    }

    private fun updateRecipeInDatabase() {
        launch {
            this.let {
                val updateRecipe = Recipes(
                    idRecipes = idRecipe.toInt(),
                    categoryName = spinnerCategory.selectedItem.toString(),
                    recipeName = tvDishName.text.toString(),
                    ingredients = tvIngredients.text.toString(),
                    instructions = tvInstructions.text.toString(),
                    recipeImage = tvImageURL.text.toString()
                )

                RecipeDatabase.getDatabase(this@AddEditActivity).recipeDao().updateRecipe(updateRecipe)
            }
        }
    }
}