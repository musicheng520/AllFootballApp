package org.wit.allfootballapp.data.remote.dto.player


import com.google.gson.annotations.SerializedName

data class Parameters(
    @SerializedName("id")
    val id: String,
    @SerializedName("season")
    val season: String
)