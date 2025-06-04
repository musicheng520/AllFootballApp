package org.wit.allfootballapp.data.remote.dto.player


import com.google.gson.annotations.SerializedName

data class Passes(
    @SerializedName("accuracy")
    val accuracy: Any,
    @SerializedName("key")
    val key: Any,
    @SerializedName("total")
    val total: Int
)