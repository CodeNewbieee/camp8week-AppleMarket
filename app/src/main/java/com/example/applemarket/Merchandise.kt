package com.example.applemarket

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Merchandise (val photo: Int, val title: String, val intro: String, val seller: String,
                        val price: Int, val address: String, var good:Int, var chat: Int, var isLike: Boolean) : Parcelable