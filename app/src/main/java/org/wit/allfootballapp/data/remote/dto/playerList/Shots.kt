package org.wit.allfootballapp.data.remote.dto.playerList


import com.google.gson.annotations.SerializedName

data class Shots(
    @SerializedName("on")
    val on: Int,
    @SerializedName("total")
    val total: Int
)