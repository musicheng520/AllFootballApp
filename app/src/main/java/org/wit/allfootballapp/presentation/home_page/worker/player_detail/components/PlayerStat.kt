import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape

import androidx.compose.ui.platform.LocalConfiguration

import androidx.compose.ui.unit.Dp

import androidx.compose.ui.unit.dp

import androidx.compose.ui.text.font.FontWeight

@Composable
fun PlayerStat(
    statName: String,
    statValue: Int,
    statMaxValue: Int,
    statColor: Color,
    height: Dp = 28.dp,
    animDuration: Int = 1000,
    animDelay: Int = 0
) {
    var animationPlayed by remember { mutableStateOf(false) }

    // 安全计算百分比，防止除以0导致NaN
    val targetPercentage = remember(statValue, statMaxValue) {
        if (statMaxValue != 0) {
            statValue / statMaxValue.toFloat()
        } else {
            0f
        }
    }

    val curPercentage by animateFloatAsState(
        targetValue = if (animationPlayed) targetPercentage else 0f,
        animationSpec = tween(
            animDuration,
            animDelay
        ),
        label = "StatAnimation"
    )

    LaunchedEffect(Unit) {
        animationPlayed = true
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(height)
            .clip(CircleShape)
            .background(
                if (isSystemInDarkTheme()) {
                    Color(0xFF505050)
                } else {
                    Color.LightGray
                }
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(curPercentage)
                .clip(CircleShape)
                .background(statColor)
                .padding(horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "$statName: $statValue", // 合并成一句
                modifier = Modifier
                    .basicMarquee(),
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }

    }
}
