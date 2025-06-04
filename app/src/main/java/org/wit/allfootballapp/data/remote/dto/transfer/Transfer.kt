package org.wit.allfootballapp.data.remote.dto.transfer


import com.google.gson.annotations.SerializedName

data class Transfer(
    @SerializedName("date")
    val date: String,
    @SerializedName("teams")
    val teams: Teams,
    @SerializedName("type")
    val type: String
)