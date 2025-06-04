package org.wit.allfootballapp.domain.repository

import org.wit.allfootballapp.domain.model.team.TeamInfo


interface TeamRepository {
    suspend fun getTeamInfo(teamId:Int): TeamInfo
}