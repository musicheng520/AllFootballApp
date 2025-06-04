package org.wit.allfootballapp.data.remote.dto.fixture


import com.google.gson.annotations.SerializedName

data class Status(
    @SerializedName("elapsed")
    val elapsed: Int,
    @SerializedName("extra")
    val extra: Any,
    @SerializedName("long")
    val long: String,
    @SerializedName("short")
    val short: String
)