package org.wit.allfootballapp.data.remote.dto.transfer


import com.google.gson.annotations.SerializedName

data class Parameters(
    @SerializedName("team")
    val team: String
)