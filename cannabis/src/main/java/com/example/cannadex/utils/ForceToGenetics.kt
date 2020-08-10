package com.example.cannadex.utils

import com.example.cannadex.data.model.Genetics
import com.squareup.moshi.*

@JsonQualifier
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FIELD, AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.FUNCTION)
annotation class ForceToGenetics

internal class ForceToGeneticsJsonAdapter {
    private val adapter by lazy {
        Moshi.Builder().add(ForceToStringJsonAdapter()).build().adapter(Genetics::class.java)
    }

    @ToJson
    fun toJson(@ForceToGenetics genetics: Genetics) = adapter.toJson(genetics)

    @FromJson
    @ForceToGenetics
    fun fromJson(reader: JsonReader): Genetics? {
        return when (reader.peek()) {
            JsonReader.Token.BOOLEAN -> {
                reader.nextBoolean()
                null
            }
            else -> adapter.fromJson(reader)
        }
    }
}