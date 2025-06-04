package org.wit.allfootballapp.data.remote.dto.player


import com.google.gson.annotations.SerializedName

data class Dribbles(
    @SerializedName("attempts")
    val attempts: Any,
    @SerializedName("past")
    val past: Any,
    @SerializedName("success")
    val success: Any
)