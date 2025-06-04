package org.wit.allfootballapp.data.remote.dto.coach


import com.google.gson.annotations.SerializedName

data class TeamX(
    @SerializedName("id")
    val id: Int,
    @SerializedName("logo")
    val logo: String,
    @SerializedName("name")
    val name: String
)