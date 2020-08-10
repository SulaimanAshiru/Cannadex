package com.example.cannadex.data.model

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class SeedCompany(
    val name: String? = null,
    val ocpc: String? = null
) : Parcelable