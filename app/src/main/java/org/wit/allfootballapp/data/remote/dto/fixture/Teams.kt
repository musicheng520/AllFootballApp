package org.wit.allfootballapp.data.remote.dto.fixture


import com.google.gson.annotations.SerializedName

data class Teams(
    @SerializedName("away")
    val away: Away,
    @SerializedName("home")
    val home: Home
)