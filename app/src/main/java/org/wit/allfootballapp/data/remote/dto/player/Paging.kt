package org.wit.allfootballapp.data.remote.dto.player


import com.google.gson.annotations.SerializedName

data class Paging(
    @SerializedName("current")
    val current: Int,
    @SerializedName("total")
    val total: Int
)