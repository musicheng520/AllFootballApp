package org.wit.allfootballapp.data.remote.dto.transfer


import com.google.gson.annotations.SerializedName

data class Player(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)