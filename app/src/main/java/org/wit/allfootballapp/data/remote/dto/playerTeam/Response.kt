package org.wit.allfootballapp.data.remote.dto.playerTeam


import com.google.gson.annotations.SerializedName

data class Response(
    @SerializedName("seasons")
    val seasons: List<Int>,
    @SerializedName("team")
    val team: Team
)