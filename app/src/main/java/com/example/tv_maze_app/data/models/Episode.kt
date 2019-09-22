package com.example.tv_maze_app.data.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "tblEpisode")
@Parcelize
data class Episode(

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

        @ColumnInfo(name = "season")
        @SerializedName("season")
        var season: Int = 0,

        @ColumnInfo(name = "number")
        @SerializedName("number")
        var number: Int = 0,

        @ColumnInfo(name = "airdate")
        @SerializedName("airdate")
        var airdate: String = "",

        @ColumnInfo(name = "airstamp")
        @SerializedName("airstamp")
        var airstamp: String = "",

        @Ignore
        @SerializedName("image")
        var image: Image,

        @ColumnInfo(name = "summary")
        @SerializedName("summary")
        var summary: String = ""
) : Parcelable