package org.wit.allfootballapp.data.remote.dto.playerList


import com.google.gson.annotations.SerializedName

data class Response(
    @SerializedName("player")
    val player: Player,
    @SerializedName("statistics")
    val statistics: List<Statistic>
)