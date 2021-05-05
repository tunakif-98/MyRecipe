package com.example.myrecipe.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.myrecipe.entities.Categories
import com.example.myrecipe.entities.Recipes


@Dao
interface RecipeDao {
    //Categories
    @Query("SELECT * FROM categories ORDER BY idCategory DESC")
    suspend fun getAllCategories() : List<Categories>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategory(categories: Categories?)

    @Query("DELETE FROM categories")
    suspend fun clearCategories()

    //Recipes
    @Query("SELECT * FROM recipes WHERE categoryName = :categoryName ORDER BY idRecipes DESC")
    suspend fun getSpecificRecipeCategory(categoryName: String) : List<Recipes>

    @Query("SELECT * FROM recipes WHERE idRecipes = :idRecipes")
    suspend fun getSpecificRecipe(idRecipes: String) : List<Recipes>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipe(recipes: Recipes?)

    @Update
    suspend fun updateRecipe(recipes: Recipes)

    @Query("DELETE FROM recipes WHERE idRecipes =:idRecipes")
    suspend fun deleteRecipe(idRecipes: String)

    @Query("SELECT * FROM recipes")
    suspend fun getAllRecipe(): List<Recipes>

    @Query("DELETE FROM recipes")
    suspend fun clearRecipes()

    @Query("SELECT * FROM recipes")
    fun isDatabaseEmpty(): LiveData<List<Recipes?>?>?
}