package org.wit.allfootballapp.data.remote.dto.playerTeam


import com.google.gson.annotations.SerializedName

data class Parameters(
    @SerializedName("player")
    val player: String
)