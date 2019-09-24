package com.example.tv_maze_app.data.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "tblCharacter")
@Parcelize
data class Character(

        @PrimaryKey
        @ColumnInfo(name = "id")
        @SerializedName("id")
        var id: Long? = 0L,

        @ColumnInfo(name = "url")
        @SerializedName("url")
        var url: String? = "",

        @ColumnInfo(name = "name")
        @SerializedName("name")
        var name: String? = "",

        @Ignore
        @SerializedName("image")
        var image: Image
) : Parcelable {
    constructor() : this(0L, "", "", Image())
}