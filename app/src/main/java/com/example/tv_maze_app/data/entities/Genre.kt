package com.example.tv_maze_app.data.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "tblGenre")
@Parcelize
data class Genre(

        @PrimaryKey
        @ColumnInfo(name = "id")
        val id: Long? = 0L,

        @ColumnInfo(name = "genre_1")
        @SerializedName("0")
        val genre1: String? = "",

        @ColumnInfo(name = "genre_2")
        @SerializedName("1")
        val genre2: String? = "",

        @ColumnInfo(name = "genre_3")
        @SerializedName("2")
        val genre3: String? = ""
) : Parcelable