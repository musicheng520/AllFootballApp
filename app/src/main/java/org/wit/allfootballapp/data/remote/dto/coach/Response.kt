package org.wit.allfootballapp.data.remote.dto.coach


import com.google.gson.annotations.SerializedName

data class Response(
    @SerializedName("age")
    val age: Int,
    @SerializedName("birth")
    val birth: Birth,
    @SerializedName("career")
    val career: List<Career>,
    @SerializedName("firstname")
    val firstname: String,
    @SerializedName("height")
    val height: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("lastname")
    val lastname: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("nationality")
    val nationality: String,
    @SerializedName("photo")
    val photo: String,
    @SerializedName("team")
    val team: TeamX,
    @SerializedName("weight")
    val weight: String
)