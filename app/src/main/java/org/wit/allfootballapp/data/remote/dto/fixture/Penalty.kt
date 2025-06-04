package org.wit.allfootballapp.data.remote.dto.fixture


import com.google.gson.annotations.SerializedName

data class Penalty(
    @SerializedName("away")
    val away: Int?,
    @SerializedName("home")
    val home: Int?
)