package com.example.myrecipe.entities

import android.media.Image
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Recipes")
data class Recipes (
    @PrimaryKey(autoGenerate = true) var idRecipes:Int,

    @ColumnInfo(name = "categoryName")
    @Expose
    @SerializedName("categoryName")
    var categoryName:String,

    @ColumnInfo(name = "recipeImage")
    @Expose
    @SerializedName("recipeImage")
    var recipeImage: String,

    @ColumnInfo(name = "recipeName")
    @Expose
    @SerializedName("recipeName")
    var recipeName: String,

    @ColumnInfo(name = "ingredients")
    @Expose
    @SerializedName("ingredients")
    var ingredients: String,

    @ColumnInfo(name = "instructions")
    @Expose
    @SerializedName("instructions")
    var instructions: String,
    )