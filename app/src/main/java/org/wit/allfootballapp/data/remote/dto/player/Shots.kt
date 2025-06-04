package org.wit.allfootballapp.data.remote.dto.player


import com.google.gson.annotations.SerializedName

data class Shots(
    @SerializedName("on")
    val on: Any,
    @SerializedName("total")
    val total: Any
)