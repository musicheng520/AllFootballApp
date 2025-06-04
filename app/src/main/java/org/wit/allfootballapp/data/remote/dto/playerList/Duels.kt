package org.wit.allfootballapp.data.remote.dto.playerList


import com.google.gson.annotations.SerializedName

data class Duels(
    @SerializedName("total")
    val total: Int,
    @SerializedName("won")
    val won: Int
)