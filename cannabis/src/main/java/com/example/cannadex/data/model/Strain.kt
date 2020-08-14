package com.example.cannadex.data.model

import android.os.Parcelable
import com.example.cannadex.utils.ForceToGenetics
import com.example.cannadex.utils.ForceToLineage
import com.example.cannadex.utils.ForceToList
import com.example.cannadex.utils.ViewType
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Strain(
    val name: String? = null,
    val ocpc: String? = null,
    val seedCompany: SeedCompany? = null,
    val qr: String? = null,
    val url: String? = null,
    val image: String? = null,
    @ForceToLineage val lineage: Lineage? = null,
    @ForceToGenetics val genetics: Genetics? = null,
    @ForceToList val children: List<String>? = null,
    val createdAt: String? = null,
    val updatedAt: String? = null
) : Parcelable {

    @IgnoredOnParcel
    var viewType = ViewType.ITEM
}