package org.wit.allfootballapp.data.repository

import org.wit.allfootballapp.data.mapper.toCoachInfo
import org.wit.allfootballapp.data.remote.FootballApi
import org.wit.allfootballapp.domain.model.coach.CoachInfo
import org.wit.allfootballapp.domain.repository.CoachRepository
import javax.inject.Inject

class CoachRepositoryImpl @Inject constructor(
    private val api: FootballApi
) : CoachRepository {
    override suspend fun getCoachByTeam(teamId: Int): CoachInfo {
        return api.getCoach(teamId).toCoachInfo()
            ?:throw Exception("No coach info found for teamId: $teamId")
    }
}