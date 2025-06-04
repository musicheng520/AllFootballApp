package org.wit.allfootballapp.data.remote.dto.fixture


import com.google.gson.annotations.SerializedName

data class Periods(
    @SerializedName("first")
    val first: Int,
    @SerializedName("second")
    val second: Int
)