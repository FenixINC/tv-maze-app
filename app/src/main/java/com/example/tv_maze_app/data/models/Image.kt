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

        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id")
        var id: Long? = 0L,

        @ColumnInfo(name = "url_medium")
        @SerializedName("medium")
        var medium: String? = "",

        @ColumnInfo(name = "url_original")
        @SerializedName("original")
        var original: String? = "",

        @ColumnInfo(name = "tv_show_id")
        var tvShowId: Long? = 0L
) : Parcelable {
    constructor() : this(null, "", "", 0L)
}