package com.example.perfumeapp.database


import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize


@Entity(tableName = "perfume_table")
@Parcelize
data class Perfume(
    @PrimaryKey(autoGenerate = true)
    var perfumeId: Long = 0L,

    @ColumnInfo(name="image_url")
    var imageUrl: String,

    @ColumnInfo(name = "perfume_name")
    val perfumeName: String,

    @ColumnInfo(name = "perfume_category")
    var perfumeCategory: String,

    @ColumnInfo(name= "perfume_brand")
    var perfumeBrand: String,

    @ColumnInfo(name = "price")
    var price: Int): Parcelable {

}