package org.wit.allfootballapp.data.remote.dto.error


import com.google.gson.annotations.SerializedName

data class ErrorDto(
    @SerializedName("errors")
    val errors: Errors,
    @SerializedName("get")
    val `get`: String,
    @SerializedName("paging")
    val paging: Paging,
    @SerializedName("parameters")
    val parameters: Parameters,
    @SerializedName("response")
    val response: List<Any>,
    @SerializedName("results")
    val results: Int
)