package com.harissabil.glogslite.data.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "game")
@Parcelize
data class GameEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "image", defaultValue = "")
    var image: String,

    @ColumnInfo(name = "guid")
    var guid: String,

    @ColumnInfo(name = "platform")
    var platform: String? = null,

    ) : Parcelable