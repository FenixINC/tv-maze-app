package com.example.tv_maze_app.data.entities

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

        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "local_id")
        var localId: Long? = 0L,

        @ColumnInfo(name = "id")
        @SerializedName("id")
        var id: Long? = 0,

        @ColumnInfo(name = "name")
        @SerializedName("name")
        var name: String? = "",

        @Ignore
        @SerializedName("country")
        var country: Country,

        @ColumnInfo(name = "tv_show_id")
        var tvShowId: Long? = 0L
) : Parcelable {
    constructor() : this(null, 0L, "", Country(), 0L)
}