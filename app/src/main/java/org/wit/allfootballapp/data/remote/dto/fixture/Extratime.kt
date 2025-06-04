package org.wit.allfootballapp.data.remote.dto.fixture


import com.google.gson.annotations.SerializedName

data class Extratime(
    @SerializedName("away")
    val away: Int,
    @SerializedName("home")
    val home: Int
)