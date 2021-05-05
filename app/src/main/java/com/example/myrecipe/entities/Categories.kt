package com.example.myrecipe.entities

import android.media.Image
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Categories")
data class Categories (
        @PrimaryKey
        var idCategory: Int,

        @ColumnInfo(name = "categoryName")
        @Expose
        @SerializedName("categoryName")
        var categoryName: String,

        @ColumnInfo(name = "categoryImage")
        @Expose
        @SerializedName("categoryImage")
        var categoryImage: String
        )