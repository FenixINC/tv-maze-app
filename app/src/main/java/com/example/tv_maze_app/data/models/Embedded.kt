package com.example.tv_maze_app.data.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "tblEmbedded")
@Parcelize
data class Embedded(

        @PrimaryKey
        @ColumnInfo(name = "id")
        var id: Long? = 0L,

        @Ignore
        @SerializedName("cast")
        var castList: List<Cast>
) : Parcelable {
    constructor() : this(0L, listOf())
}