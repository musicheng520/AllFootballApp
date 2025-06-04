package org.wit.allfootballapp.domain.model.playerList


data class Player(
    val id: Int,
    val name: String,
    val firstName: String,
    val lastName: String,
    val age: Int,
    val birthDate: String,
    val birthPlace: String,
    val birthCountry: String,
    val nationality: String,
    val height: String?,
    val weight: String?,
    val photoUrl: String
)

data class PlayerStatistic(
    val teamName: String,
    val teamLogo: String,
    val gamesPlayed: Int,
    val position: String,
    val rating: String?,
    val goals: Int,
    val assists: Int,
    val yellowCards: Int,
    val redCards: Int
)

data class PlayerWithStats(
    val player: Player,
    val statistics: List<PlayerStatistic>
)
