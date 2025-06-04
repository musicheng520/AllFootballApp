package org.wit.allfootballapp.data.remote.dto.transfer


import com.google.gson.JsonElement
import com.google.gson.annotations.SerializedName

data class TransferDto(
    @SerializedName("errors")
    val errors: Any?,
    @SerializedName("get")
    val `get`: String,
    @SerializedName("paging")
    val paging: Paging,
    @SerializedName("parameters")
    val parameters: Parameters,
    @SerializedName("response")
    val response: List<Response>,
    @SerializedName("results")
    val results: Int
)