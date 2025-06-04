package org.wit.allfootballapp.data.remote.dto.fixture


import com.google.gson.annotations.SerializedName

data class Response(
    @SerializedName("fixture")
    val fixture: Fixture,
    @SerializedName("goals")
    val goals: Goals,
    @SerializedName("league")
    val league: League,
    @SerializedName("score")
    val score: Score,
    @SerializedName("teams")
    val teams: Teams
)