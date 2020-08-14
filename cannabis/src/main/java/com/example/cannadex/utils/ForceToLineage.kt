package com.example.cannadex.utils

import com.example.cannadex.data.model.Lineage
import com.squareup.moshi.*

@JsonQualifier
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FIELD, AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.FUNCTION)
annotation class ForceToLineage

internal class ForceToLineageJsonAdapter {
    private val adapter by lazy {
        Moshi.Builder().add(ForceToStringJsonAdapter()).build().adapter(Lineage::class.java)
    }

    @ToJson
    fun toJson(@ForceToLineage lineage: Lineage) = adapter.toJson(lineage)

    @FromJson
    @ForceToLineage
    fun fromJson(reader: JsonReader): Lineage? {
        return when (reader.peek()) {
            JsonReader.Token.BEGIN_ARRAY -> {
                reader.readJsonValue()
                null
            }
            else -> adapter.fromJson(reader)
        }
    }
}