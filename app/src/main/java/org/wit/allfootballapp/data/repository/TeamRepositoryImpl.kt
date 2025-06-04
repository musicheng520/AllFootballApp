package org.wit.allfootballapp.data.repository

import org.wit.allfootballapp.data.mapper.toTeamInfo
import org.wit.allfootballapp.data.remote.FootballApi
import org.wit.allfootballapp.domain.model.team.TeamInfo
import org.wit.allfootballapp.domain.repository.TeamRepository
import javax.inject.Inject

class TeamRepositoryImpl @Inject constructor(
    private val api: FootballApi
) : TeamRepository {
    override suspend fun getTeamInfo(teamId: Int):TeamInfo {
        return api.getTeam(teamId).toTeamInfo()
            ?: throw Exception("No team info found for teamId: $teamId")
    }
}