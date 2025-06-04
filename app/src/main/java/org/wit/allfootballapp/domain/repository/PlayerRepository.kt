package org.wit.allfootballapp.domain.repository

import kotlinx.coroutines.flow.Flow
import org.wit.allfootballapp.domain.model.playerList.PlayerWithStats
import org.wit.allfootballapp.domain.model.playerTeam.PlayerTeam

interface PlayerRepository {

    suspend fun getPlayersByTeam(teamId: Int, season: Int):List<PlayerWithStats>
    suspend fun getTeamsByPlayer(playerId: Int):List<PlayerTeam>

}