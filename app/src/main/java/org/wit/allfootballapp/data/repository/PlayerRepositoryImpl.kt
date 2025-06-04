package org.wit.allfootballapp.data.repository

import org.wit.allfootballapp.data.mapper.toPlayerTeam
import org.wit.allfootballapp.data.mapper.toPlayerWithStatsList
import org.wit.allfootballapp.data.remote.FootballApi
import org.wit.allfootballapp.domain.model.playerList.PlayerWithStats
import org.wit.allfootballapp.domain.model.playerTeam.PlayerTeam
import org.wit.allfootballapp.domain.repository.PlayerRepository
import javax.inject.Inject

class PlayerRepositoryImpl @Inject constructor(
    private val api: FootballApi
) : PlayerRepository {
    override suspend fun getPlayersByTeam(
        teamId: Int,
        season: Int
    ): List<PlayerWithStats> {
        return api.getPlayersByTeam(teamId,season).toPlayerWithStatsList()
    }

    override suspend fun getTeamsByPlayer(playerId: Int): List<PlayerTeam> {
        return api.getPlayerTeam(playerId).toPlayerTeam()
    }


}