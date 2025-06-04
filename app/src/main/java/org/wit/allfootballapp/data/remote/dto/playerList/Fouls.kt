package org.wit.allfootballapp.data.remote.dto.playerList


import com.google.gson.annotations.SerializedName

data class Fouls(
    @SerializedName("committed")
    val committed: Int,
    @SerializedName("drawn")
    val drawn: Int
)