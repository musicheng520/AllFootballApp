package org.wit.allfootballapp.data.mapper

import org.wit.allfootballapp.data.remote.dto.player.PlayerInfoDto
import org.wit.allfootballapp.data.remote.dto.playerList.PlayerListDto
import org.wit.allfootballapp.data.remote.dto.playerTeam.PlayerTeamDto
import org.wit.allfootballapp.domain.model.playerInfo.PlayerInfo
import org.wit.allfootballapp.domain.model.playerList.Player
import org.wit.allfootballapp.domain.model.playerList.PlayerStatistic
import org.wit.allfootballapp.domain.model.playerList.PlayerWithStats

import org.wit.allfootballapp.domain.model.playerTeam.PlayerTeam

fun PlayerTeamDto.toPlayerTeam():List<PlayerTeam>{
    return response.map { response->
        PlayerTeam(
            teamId = response.team.id,
            teamName = response.team.name,
            logoUrl = response.team.logo,
            seasons = response.seasons
        )
    }
}

fun PlayerListDto.toPlayerWithStatsList(): List<PlayerWithStats> {
    return response.map { resp ->
        PlayerWithStats(
            player = Player(
                id = resp.player.id,
                name = resp.player.name,
                firstName = resp.player.firstname,
                lastName = resp.player.lastname,
                age = resp.player.age,
                birthDate = resp.player.birth.date,
                birthPlace = resp.player.birth.place,
                birthCountry = resp.player.birth.country,
                nationality = resp.player.nationality,
                height = resp.player.height,
                weight = resp.player.weight,
                photoUrl = resp.player.photo
            ),
            statistics = resp.statistics.map { stat ->
                PlayerStatistic(
                    teamName = stat.team.name,
                    teamLogo = stat.team.logo,
                    gamesPlayed = stat.games.appearences,
                    position = stat.games.position,
                    rating = stat.games.rating,
                    goals = stat.goals.total,
                    assists = stat.goals.assists,
                    yellowCards = stat.cards.yellow,
                    redCards = stat.cards.red
                )
            }
        )
    }
}


