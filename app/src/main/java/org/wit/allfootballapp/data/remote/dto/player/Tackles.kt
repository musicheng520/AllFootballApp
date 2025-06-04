package org.wit.allfootballapp.data.remote.dto.player


import com.google.gson.annotations.SerializedName

data class Tackles(
    @SerializedName("blocks")
    val blocks: Any,
    @SerializedName("interceptions")
    val interceptions: Any,
    @SerializedName("total")
    val total: Int
)