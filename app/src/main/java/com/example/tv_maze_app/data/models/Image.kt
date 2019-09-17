package com.example.tv_maze_app.data.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "tblImage")
@Parcelize
data class Image(

        @PrimaryKey
        @ColumnInfo(name = "id")
        val id: Long = 0L,

        @ColumnInfo(name = "url_medium")
        @SerializedName("medium")
        val medium: String = "",

        @ColumnInfo(name = "url_original")
        @SerializedName("original")
        val original: String = ""
) : Parcelable