package com.example.tv_maze_app.data.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "tblSeason")
@Parcelize
data class Season(

        @PrimaryKey
        @ColumnInfo(name = "id")
        var id: Long? = 0L,

        @ColumnInfo(name = "season")
        var season: Int? = 0
) : Parcelable