package com.example.cannadex.utils

import com.squareup.moshi.*

@JsonQualifier
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FIELD, AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.FUNCTION)
annotation class ForceToList

internal class ForceToListJsonAdapter {
    private val adapter by lazy {
        Types.newParameterizedType(List::class.java, String::class.java).let {
            Moshi.Builder().build().adapter<List<String>>(it)
        }
    }

    @ToJson
    fun toJson(@ForceToList list: List<String>) = adapter.toJson(list)

    @FromJson
    @ForceToList
    fun fromJson(reader: JsonReader): List<String>? {
        return when (reader.peek()) {
            JsonReader.Token.BOOLEAN -> {
                reader.nextBoolean()
                null
            }
            else -> adapter.fromJson(reader)
        }
    }
}