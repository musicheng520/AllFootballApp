package org.wit.allfootballapp.data.remote.dto.team


import com.google.gson.annotations.SerializedName

data class Response(
    @SerializedName("team")
    val team: Team,
    @SerializedName("venue")
    val venue: Venue
)