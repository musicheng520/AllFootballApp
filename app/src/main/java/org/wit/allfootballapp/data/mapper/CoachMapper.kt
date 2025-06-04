package org.wit.allfootballapp.data.mapper

import org.wit.allfootballapp.data.remote.dto.coach.CoachDto
import org.wit.allfootballapp.data.remote.dto.coach.Response
import org.wit.allfootballapp.domain.model.coach.CoachCareer
import org.wit.allfootballapp.domain.model.coach.CoachInfo
import java.text.SimpleDateFormat
import java.util.Locale

// 主函数：CoachDto 转成 CoachInfo，只返回最新的那个教练
fun CoachDto.toCoachInfo(): CoachInfo ?{
    val currentCoaches = response.filter { resp ->
        resp.career.any { it.end == null }
    }

    val latestCoach = if (currentCoaches.isNotEmpty()) {
        // 当前任职教练中找开始日期最新的
        currentCoaches.maxByOrNull { resp ->
            resp.career.filter { it.end == null }
                .maxOfOrNull { it.start.toDateLong() ?: 0L } ?: 0L
        }
    } else {
        // 没有当前任职教练，找所有教练中开始日期最新的
        response.maxByOrNull { resp ->
            resp.career.maxOfOrNull { it.start.toDateLong() ?: 0L } ?: 0L
        }
    }

    return latestCoach?.toCoachInfo()
}

// Response 转 CoachInfo（单个教练实体转换）
fun Response.toCoachInfo(): CoachInfo {
    return CoachInfo(
        id = id,
        name = name,
        firstName = firstname,
        lastName = lastname,
        age = age,
        birthDate = birth.date,
        birthPlace = birth.place,
        birthCountry = birth.country,
        nationality = nationality,
        height = height?.takeIf { it.isNotBlank() },  // 安全调用
        weight = weight?.takeIf { it.isNotBlank() },  // 安全调用
        photoUrl = photo,
        teamId = team.id,
        teamName = team.name,
        teamLogo = team.logo,
        career = career.map {
            CoachCareer(
                teamId = it.team.id,
                teamName = it.team.name,
                logoUrl = it.team.logo,
                startDate = it.start,
                endDate = it.end
            )
        }
    )
}

// 扩展函数：字符串日期转Long
fun String.toDateLong(format: String = "yyyy-MM-dd"): Long? {
    return try {
        val sdf = SimpleDateFormat(format, Locale.getDefault())
        sdf.parse(this)?.time
    } catch (e: Exception) {
        null
    }
}
