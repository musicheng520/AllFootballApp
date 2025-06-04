package org.wit.allfootballapp.domain.model.playerTeam

data class PlayerTeam(
    val teamId: Int,
    val teamName: String,
    val logoUrl: String,
    val seasons: List<Int>
)
