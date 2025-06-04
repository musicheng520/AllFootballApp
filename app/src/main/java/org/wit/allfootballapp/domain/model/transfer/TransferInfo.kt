package org.wit.allfootballapp.domain.model.transfer

data class TransferInfo(
    val player: Player,
    val transfers: List<Transfer>
)

data class Player(
    val id: Int,
    val name: String
)

data class Transfer(
    val date: String,
    val type: String,
    val teams: TransferTeams
)

data class TransferTeams(
    val `in`: TeamInfo,
    val `out`: TeamInfo
)

data class TeamInfo(
    val id: Int,
    val name: String,
    val logo: String
)

