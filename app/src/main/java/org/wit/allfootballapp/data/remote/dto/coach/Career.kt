package org.wit.allfootballapp.data.remote.dto.coach


import com.google.gson.annotations.SerializedName

data class Career(
    @SerializedName("end")
    val end: String,
    @SerializedName("start")
    val start: String,
    @SerializedName("team")
    val team: TeamX
)