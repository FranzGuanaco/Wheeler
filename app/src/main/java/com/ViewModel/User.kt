package com.Model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo

@Entity(tableName = "contact")

data class User (
    @PrimaryKey (autoGenerate = true) val id: Int?,
    @ColumnInfo (name = "name") val nme: String?,
        )


