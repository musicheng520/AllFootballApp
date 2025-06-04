package org.wit.allfootballapp.data.remote.dto.player


import com.google.gson.annotations.SerializedName

data class Penalty(
    @SerializedName("commited")
    val commited: Any,
    @SerializedName("missed")
    val missed: Int,
    @SerializedName("saved")
    val saved: Int,
    @SerializedName("scored")
    val scored: Int,
    @SerializedName("won")
    val won: Any
)