package com.example.cannadex.data.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class StrainsResponse(
    @Json(name = "data")
    val strains: List<Strain>? = null,
    val meta: Meta? = null
) : Parcelable