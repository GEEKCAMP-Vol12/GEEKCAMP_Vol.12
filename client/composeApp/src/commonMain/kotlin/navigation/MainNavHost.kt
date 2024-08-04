package healthapp.composeapp

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.Serializable
import screen.HomeScreen
import screen.MyLogScreen
import screen.RankingScreen
import screen.SettingScreen

@Composable
fun MainNavHost(
    modifier: Modifier = Modifier,
    startDestination: NavigationDestination = NavigationDestination.Home,
) {
    val navController = rememberNavController()
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination,
    ) {
        composable<NavigationDestination.Home> {
            HomeScreen(navController = navController)
        }
        composable<NavigationDestination.Ranking> {
            RankingScreen(navController = navController)
        }
        composable<NavigationDestination.MyLog> {
            MyLogScreen(navController = navController)
        }
        composable<NavigationDestination.Setting> {
            SettingScreen(navController = navController)
        }
    }
}

@Serializable
sealed class NavigationDestination {
    @Serializable
    data object Home : NavigationDestination()

    @Serializable
    data object Ranking: NavigationDestination()

    @Serializable
    data object MyLog: NavigationDestination()

    @Serializable
    data object Setting: NavigationDestination()
}
