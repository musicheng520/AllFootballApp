package org.wit.allfootballapp.data.remote.dto.fixture


import com.google.gson.annotations.SerializedName

data class Away(
    @SerializedName("id")
    val id: Int,
    @SerializedName("logo")
    val logo: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("winner")
    val winner: Boolean
)