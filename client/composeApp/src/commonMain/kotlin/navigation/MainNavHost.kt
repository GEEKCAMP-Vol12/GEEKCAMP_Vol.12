package navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import data.model.Caffeine
import data.model.Lce
import data.model.Sleep
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format.char
import kotlinx.datetime.plus
import kotlinx.datetime.toLocalDateTime
import kotlinx.serialization.Serializable
import screen.HealthStatus
import screen.HomeScreen
import screen.HomeScreenArg
import screen.MyLogScreen
import screen.RankingScreen
import screen.SettingScreen
import viewmodel.HealthAppViewModel

@Composable
fun MainNavHost(
    modifier: Modifier = Modifier,
    startDestination: NavigationDestination = NavigationDestination.Home,
    healthAppViewModel: HealthAppViewModel,
) {
    val healthAppViewState = healthAppViewModel.appViewState.collectAsState().value
    val navController = rememberNavController()

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination,
    ) {
        composable<NavigationDestination.Home> {
            val homeScreenArg = healthAppViewState.getIfContent()?.let {
                createHomeScreenArg(it.currentWeekSleepData, it.currentWeekCaffeineData)
            } ?: Lce.Loading
            HomeScreen(
                homeScreenArg = homeScreenArg,
                navController = navController,
                updateUserData = healthAppViewModel::updateUserData,
            )
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

private fun createHomeScreenArg(
    weekSleepData: List<Sleep>?,
    weekCaffeineData: List<Caffeine>?,
): Lce<HomeScreenArg> {
    if (weekSleepData == null || weekCaffeineData == null) {
        return Lce.Content(HomeScreenArg.INITIAL_VALUE)
    }
    val averageSleepMin = (weekSleepData.sumOf { it.lengthMin } / weekSleepData.size)
    val averageCaffeineMg = weekCaffeineData.sumOf { it.amountMg } / weekCaffeineData.size
    val systemTZ = TimeZone.currentSystemDefault()
    val dateFormat = LocalDate.Format {
        year()
        char('/')
        monthNumber()
        char('/')
        dayOfMonth()
    }
    val startMS = Clock.System.now()
    val endMS = startMS.plus(7, DateTimeUnit.DAY, systemTZ)
    println("averageSleepHour: $averageSleepMin point ${Sleep.calculateScore(averageSleepMin)}")
    println("averageCaffeineMg: $averageCaffeineMg point ${Caffeine.calculateScore(averageCaffeineMg)}")
    val totalPoint =
        Sleep.calculateScore(averageSleepMin) + Caffeine.calculateScore(averageCaffeineMg)
    println("totalPoint: $totalPoint")
    val healthStatus = when {
        totalPoint == 0 -> HealthStatus.Good
        totalPoint <= 100 -> HealthStatus.Mid
        totalPoint <= 200 -> HealthStatus.Bad
        else -> HealthStatus.Down
    }
    return Lce.Content(
        HomeScreenArg(
            startDate = dateFormat.format(startMS.toLocalDateTime(systemTZ).date),
            endDate = dateFormat.format(endMS.toLocalDateTime(systemTZ).date),
            averageSleepHour = "${averageSleepMin / 60f}",
            averageCaffeineMg = "${averageCaffeineMg}",
            healthStatus = healthStatus,
        )
    )
}


@Serializable
sealed class NavigationDestination {
    @Serializable
    data object Home : NavigationDestination()

    @Serializable
    data object Ranking : NavigationDestination()

    @Serializable
    data object MyLog : NavigationDestination()

    @Serializable
    data object Setting : NavigationDestination()
}
