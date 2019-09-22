package com.example.tv_maze_app.data.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "tblCrew")
@Parcelize
data class Crew(

        @PrimaryKey
        @ColumnInfo(name = "id")
        var id: Long = 0L,

        @ColumnInfo(name = "type")
        @SerializedName("type")
        var type: String = "",

        @Ignore
        @SerializedName("person")
        var person: Person
) : Parcelable