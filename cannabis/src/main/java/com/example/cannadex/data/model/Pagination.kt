package com.example.cannadex.data.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Pagination(
    val total: Int? = null,
    val count: Int? = null,
    @Json(name = "per_page")
    val perPage: Int? = null,
    @Json(name = "current_page")
    val currentPage: Int? = null,
    @Json(name = "total_pages")
    val totalPages: Int? = null,
    val links: Links? = null
) : Parcelable