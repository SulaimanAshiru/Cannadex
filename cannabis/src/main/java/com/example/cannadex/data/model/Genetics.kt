package com.example.cannadex.data.model

import android.os.Parcelable
import com.example.cannadex.utils.ForceToString
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Genetics(
    @ForceToString val names: String? = null,
    @ForceToString val ocpc: String? = null
) : Parcelable