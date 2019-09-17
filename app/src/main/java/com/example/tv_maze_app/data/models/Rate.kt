package com.example.tv_maze_app.data.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "tblRate")
@Parcelize
data class Rate(

        @PrimaryKey
        @ColumnInfo(name = "id")
        val id: Long = 0L,

        @ColumnInfo(name = "average")
        @SerializedName("average")
        val average: Double = 0.0
) : Parcelable