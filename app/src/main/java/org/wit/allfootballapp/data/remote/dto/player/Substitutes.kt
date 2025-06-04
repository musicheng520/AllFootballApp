package org.wit.allfootballapp.data.remote.dto.player


import com.google.gson.annotations.SerializedName

data class Substitutes(
    @SerializedName("bench")
    val bench: Int,
    @SerializedName("in")
    val inX: Int,
    @SerializedName("out")
    val `out`: Int
)