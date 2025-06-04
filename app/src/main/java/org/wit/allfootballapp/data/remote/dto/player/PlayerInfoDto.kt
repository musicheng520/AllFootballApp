package org.wit.allfootballapp.data.remote.dto.player


import com.google.gson.JsonElement
import com.google.gson.annotations.SerializedName

data class PlayerInfoDto(
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