package com.example.tv_maze_app.data.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "tblTvShow")
@Parcelize
data class TvShow(

        @PrimaryKey
        @ColumnInfo(name = "id")
        @SerializedName("id")
        var id: Long = 0L,

        @ColumnInfo(name = "url")
        @SerializedName("url")
        var url: String = "",

        @ColumnInfo(name = "name")
        @SerializedName("name")
        var name: String = "",

        @ColumnInfo(name = "language")
        @SerializedName("language")
        var language: String = "",

//        @Ignore
//        @SerializedName("genres")
//        var genreList: ArrayList<Genre>,

        @ColumnInfo(name = "status")
        @SerializedName("status")
        var status: String = "",

        @ColumnInfo(name = "premiered")
        @SerializedName("premiered")
        var premiered: String = "",

        @ColumnInfo(name = "official_site")
        @SerializedName("officialSite")
        var officialSite: String? = null,

        @Ignore
        @SerializedName("rating")
        var rate: Rate,

        @Ignore
        @SerializedName("network")
        var network: Network,

        @Ignore
        @SerializedName("image")
        var image: Image,

        @ColumnInfo(name = "summary")
        @SerializedName("summary")
        var summary: String = "",

        @ColumnInfo(name = "updated")
        @SerializedName("updated")
        var updated: String = "",

        @ColumnInfo(name = "is_favorite")
        var isFavorite: Boolean = false
) : Parcelable {
    constructor() : this(0L, "", "", "", "", "", "",
            Rate(), Network(), Image(), "", "", false)
}