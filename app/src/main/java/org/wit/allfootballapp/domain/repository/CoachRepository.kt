package org.wit.allfootballapp.domain.repository

import org.wit.allfootballapp.domain.model.coach.CoachInfo

interface CoachRepository {
    suspend fun getCoachByTeam(teamId:Int): CoachInfo
}