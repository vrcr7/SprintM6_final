package com.example.sprintm6.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "TABLE_PHONE")
data class Phone (
    @PrimaryKey//(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int?,
    @ColumnInfo(name = "phoneName")
    val phoneName: String,
    @ColumnInfo(name = "phonePrice")
    val phonePrice: Int,
    @ColumnInfo(name = "PhoneImage")
    val phoneImage: String,
    @ColumnInfo (name = "PhoneDescription")
    val phoneDescription: String,
    @ColumnInfo(name = "PhoneLastPrice")
    val phoneLastPrice: Int,
    @ColumnInfo(name = "PhoneCredit")
    val phoneCredit: Boolean

)

