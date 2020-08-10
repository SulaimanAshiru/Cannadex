package com.example.cannadex.data.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Lineage(
    @Json(name = "Afghanistan")
    val afghanistan: String? = null,
    @Json(name = "Colombia")
    val colombia: String? = null,
    @Json(name = "India")
    val india: String? = null,
    @Json(name = "Mexico")
    val mexico: String? = null,
    @Json(name = "Thailand")
    val thailand: String? = null
) : Parcelable