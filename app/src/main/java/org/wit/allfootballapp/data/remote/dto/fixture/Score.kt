package org.wit.allfootballapp.data.remote.dto.fixture


import com.google.gson.annotations.SerializedName

data class Score(
    @SerializedName("extratime")
    val extratime: Extratime,
    @SerializedName("fulltime")
    val fulltime: Fulltime,
    @SerializedName("halftime")
    val halftime: Halftime,
    @SerializedName("penalty")
    val penalty: Penalty
)