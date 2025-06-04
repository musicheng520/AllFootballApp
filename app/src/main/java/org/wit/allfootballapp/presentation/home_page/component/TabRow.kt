package org.wit.allfootballapp.presentation.home_page.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.SportsSoccer
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.SportsSoccer
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector


data class TabItem(
    val title: String,
    val unselectedItem: ImageVector,
    val selectedItem: ImageVector,
)



@Composable
fun TabRowScreen() {
    val tabItems = listOf(
        TabItem(
            title = "Home",
            unselectedItem = Icons.Outlined.Home,
            selectedItem = Icons.Filled.Home
        ),
        TabItem(
            title = "Player",
            unselectedItem = Icons.Outlined.SportsSoccer,
            selectedItem = Icons.Filled.SportsSoccer
        ),
        TabItem(
            title = "Info",
            unselectedItem = Icons.Outlined.Info,
            selectedItem = Icons.Filled.Info
        )
    )

    var selectedTabIndex by remember {
        mutableIntStateOf(0)
    }

    Column(modifier = Modifier.fillMaxSize()) {
        TabRow(selectedTabIndex = selectedTabIndex) {
            tabItems.forEachIndexed { index, item ->
                Tab(
                    selected = index == selectedTabIndex,
                    onClick = {
                        selectedTabIndex = index
                    },
                    text = { Text(text = item.title) },
                    icon = {
                        Icon(
                            imageVector = if (index == selectedTabIndex) {
                                item.selectedItem
                            } else item.unselectedItem,
                            contentDescription = item.title
                        )
                    }
                )
            }
        }
    }
}