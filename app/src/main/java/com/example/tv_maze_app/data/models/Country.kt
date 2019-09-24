package com.example.tv_maze_app.data.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "tblCountry")
@Parcelize
data class Country(

        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id")
        val id: Long? = 0L,

        @ColumnInfo(name = "name")
        @SerializedName("name")
        val name: String? = "",

        @ColumnInfo(name = "timezone")
        @SerializedName("timezone")
        val timezone: String? = "",

        @ColumnInfo(name = "tv_show_id")
        var tvShowId: Long? = 0L
) : Parcelable {
    constructor() : this(null, "", "", 0L)
}