package org.wit.allfootballapp.data.remote.dto.error


import com.google.gson.annotations.SerializedName

data class Errors(
    @SerializedName("requests")
    val requests: String
)