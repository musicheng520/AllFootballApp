package org.wit.allfootballapp.data.remote.dto.playerList


import com.google.gson.annotations.SerializedName

data class Parameters(
    @SerializedName("season")
    val season: String,
    @SerializedName("team")
    val team: String
)