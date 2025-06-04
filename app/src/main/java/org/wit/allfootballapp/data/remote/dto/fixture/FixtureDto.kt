package org.wit.allfootballapp.data.remote.dto.fixture


import com.google.gson.annotations.SerializedName

data class FixtureDto(
    @SerializedName("response")
    val response: List<Response>
)