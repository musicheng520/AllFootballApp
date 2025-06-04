package org.wit.allfootballapp.presentation.fixtures.components


import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import org.wit.allfootballapp.domain.model.fixture.FixtureInfo

@Composable
fun FixtureItem(fixtureInfo: FixtureInfo,
                onTeamClick: (teamId: Int) -> Unit,) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp, horizontal = 12.dp)
            .shadow(2.dp, RoundedCornerShape(10.dp)),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // 第一列：主队
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.weight(1f)
            ) {
                TeamIcon(iconUrl = fixtureInfo.homeTeam.logo, size = 48.dp,modifier= Modifier.clickable{
                    onTeamClick(fixtureInfo.homeTeam.id)
                    Log.d("FixtureItem", "Team clicked: ${fixtureInfo.homeTeam.id}")})
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = fixtureInfo.homeTeam.name, style = MaterialTheme.typography.bodyMedium)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "${fixtureInfo.goals.home}", style = MaterialTheme.typography.headlineMedium)
            }

            // 第二列：比赛信息
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = fixtureInfo.date,
                    style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = fixtureInfo.venue.name?:"",
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center
                )
            }

            // 第三列：客队
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.weight(1f)
            ) {
                TeamIcon(iconUrl = fixtureInfo.awayTeam.logo, size = 48.dp,modifier= Modifier.clickable{
                    onTeamClick(fixtureInfo.awayTeam.id)
                    Log.d("FixtureItem", "Team clicked: ${fixtureInfo.homeTeam.id}")})
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = fixtureInfo.awayTeam.name, style = MaterialTheme.typography.bodyMedium)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "${fixtureInfo.goals.away}", style = MaterialTheme.typography.headlineMedium)
            }
        }
    }
}

@Composable
fun TeamIcon(iconUrl: String, size: Dp, modifier: Modifier = Modifier) {
    AsyncImage(
        model = iconUrl,
        contentDescription = null,
        modifier = modifier.size(size), // 这里把传入的 modifier 和 size 一起用
        contentScale = ContentScale.Crop
    )
}
