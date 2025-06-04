package org.wit.allfootballapp.data.remote


import org.wit.allfootballapp.data.remote.dto.coach.CoachDto
import org.wit.allfootballapp.data.remote.dto.fixture.FixtureDto
import org.wit.allfootballapp.data.remote.dto.player.PlayerInfoDto
import org.wit.allfootballapp.data.remote.dto.playerList.PlayerListDto
import org.wit.allfootballapp.data.remote.dto.playerTeam.PlayerTeamDto
import org.wit.allfootballapp.data.remote.dto.team.TeamDto
import org.wit.allfootballapp.data.remote.dto.transfer.TransferDto

import retrofit2.http.GET
import retrofit2.http.Query

interface FootballApi {

    @GET("players")
    suspend fun getPlayersByTeam(
        @Query("team") teamId: Int,
        @Query("season") season: Int
    ): PlayerListDto

    @GET("players/teams")
    suspend fun getPlayerTeam(@Query("player") playerId: Int): PlayerTeamDto

    @GET("teams")
    suspend fun getTeam(@Query("id") teamId: Int): TeamDto

    @GET("coachs")
    suspend fun getCoach(@Query("team") teamId: Int): CoachDto

    @GET("fixtures")
    suspend fun getFixtures(
        @Query("team") teamId: Int,
        @Query("season") season: Int
    ): FixtureDto


/*
    https://v3.football.api-sports.io/players?id=19088&season=2023
*/
    @GET("players")
    suspend fun getPlayerInfo(
        @Query("id") playerId:Int,
        @Query("season") seasonId: Int
    ): PlayerInfoDto


    @GET("transfers")
    suspend fun getTransfers(
        @Query("team") teamId: Int
    ): TransferDto

}
