package org.wit.allfootballapp.data.remote.dto.player


import com.google.gson.annotations.SerializedName

data class Fouls(
    @SerializedName("committed")
    val committed: Any,
    @SerializedName("drawn")
    val drawn: Int
)