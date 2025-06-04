package org.wit.allfootballapp.data.repository

import org.wit.allfootballapp.data.mapper.toFixtureInfo
import org.wit.allfootballapp.data.remote.FootballApi
import org.wit.allfootballapp.domain.model.fixture.FixtureInfo
import org.wit.allfootballapp.domain.repository.FixtureRepository
import javax.inject.Inject

class FixtureRepositoryImpl @Inject constructor(
    private val api: FootballApi
) : FixtureRepository {
    override suspend fun getFixtureByTeam(
        teamId: Int,
        season: Int
    ): List<FixtureInfo> {
        return api.getFixtures(teamId,season).toFixtureInfo()
    }
}