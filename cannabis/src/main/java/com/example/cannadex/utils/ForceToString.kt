package com.example.cannadex.utils

import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonQualifier
import com.squareup.moshi.JsonReader
import com.squareup.moshi.ToJson

@JsonQualifier
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FIELD, AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.FUNCTION)
annotation class ForceToString

internal class ForceToStringJsonAdapter {

    @ToJson
    fun toJson(@ForceToString string: String) = string

    @FromJson
    @ForceToString
    fun fromJson(reader: JsonReader): String? {
        return when (reader.peek()) {
            JsonReader.Token.BOOLEAN -> {
                reader.nextBoolean()
                null
            }
            JsonReader.Token.STRING -> reader.nextString()
            else -> reader.nextString()
        }
    }
}