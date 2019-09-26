package com.example.tv_maze_app.data.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "tblRate")
@Parcelize
data class Rate(

        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id")
        var id: Long? = 0L,

        @ColumnInfo(name = "average")
        @SerializedName("average")
        var average: Double? = 0.0,

        @ColumnInfo(name = "tv_show_id")
        var tvShowId: Long? = 0L
) : Parcelable {
    constructor() : this(null, 0.0, 0L)
}