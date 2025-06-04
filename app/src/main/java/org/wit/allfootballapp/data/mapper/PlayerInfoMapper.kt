package org.wit.allfootballapp.data.mapper

import org.wit.allfootballapp.data.remote.dto.player.PlayerInfoDto
import org.wit.allfootballapp.domain.model.playerInfo.Player
import org.wit.allfootballapp.domain.model.playerInfo.PlayerInfo
import org.wit.allfootballapp.domain.model.playerInfo.PlayerStatistic

fun PlayerInfoDto.toPlayerInfo(): PlayerInfo {
    // 如果 response 为空，返回一个默认空对象（或抛异常，视需求）
    val resp = response.firstOrNull() ?: throw IllegalStateException("No player data in response")

    return PlayerInfo(
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
                gamesPlayed = stat.games.appearences ?: 0,
                position = stat.games.position,
                rating = stat.games.rating,
                goals = stat.goals?.total ?: 0,
                assists = stat.goals.assists ?: 0,
                yellowCards = stat.cards?.yellow ?: 0,
                redCards = stat.cards?.red ?: 0
            )
        }
    )
}
