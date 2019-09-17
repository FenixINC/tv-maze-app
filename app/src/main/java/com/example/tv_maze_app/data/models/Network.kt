package com.example.tv_maze_app.data.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "tblNetwork")
@Parcelize
data class Network(

        @PrimaryKey
        @ColumnInfo(name = "local_id")
        val localId: Long = 0L,

        @ColumnInfo(name = "id")
        @SerializedName("id")
        val id: Long = 0,

        @ColumnInfo(name = "name")
        @SerializedName("name")
        val name: String = "",

        @Ignore
        @SerializedName("country")
        val country: Country
) : Parcelable {
        constructor() : this (0L, 0L, "", Country())
}