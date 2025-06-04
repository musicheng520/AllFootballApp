package org.wit.allfootballapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dagger.hilt.android.AndroidEntryPoint
import org.wit.allfootballapp.data.remote.FootballApi
import org.wit.allfootballapp.presentation.navgraph.AppNavGraph
import org.wit.allfootballapp.ui.theme.AllFootballAppTheme
import javax.inject.Inject
/*v-1.0*/
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var footballApi: FootballApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
           /* AllFootballAppTheme {
                TestFootballApi(api = footballApi)
            }*/

            AllFootballAppTheme {
                AppNavGraph()
            }


        }
    }
}

