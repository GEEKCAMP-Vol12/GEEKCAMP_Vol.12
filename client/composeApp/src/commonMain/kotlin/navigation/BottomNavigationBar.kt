package navigation

import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.BarChart
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import healthapp.composeapp.NavigationDestination
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun BottomNavigationBar(
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    val selectedRoute = navController.currentDestination?.route
    BottomAppBar(
        modifier = modifier,
        backgroundColor = Color.White
    ) {
        bottomNavigationBarItemDataList.forEach { item ->
            BottomNavigationItem(
                selected = item.route::class.qualifiedName == selectedRoute,
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = ""
                    )
                },
                label = {
                     Text(item.text)
                },
                onClick = {
                    navController.navigate(item.route)
                },
                selectedContentColor = Color(0xFFC1A14E),
                unselectedContentColor = Color.Gray,
            )
        }
    }
}

val bottomNavigationBarItemDataList = listOf(
    BottomNavigationItemData(
        route = NavigationDestination.Home,
        text = "ホーム",
        icon = Icons.Rounded.Home,
    ),
    BottomNavigationItemData(
        route = NavigationDestination.Ranking,
        text = "ランキング",
        icon = Icons.Rounded.BarChart
    ),
    BottomNavigationItemData(
        route = NavigationDestination.MyLog,
        text = "自分の記録",
        icon = Icons.Rounded.Person,
    ),
    BottomNavigationItemData(
        route = NavigationDestination.Setting,
        text = "設定",
        icon = Icons.Rounded.Settings,
    ),
)

data class BottomNavigationItemData(
    val route: NavigationDestination,
    val text: String,
    val icon: ImageVector
)

@Preview
@Composable
fun BottomNavigationBarPreview() {
    BottomNavigationBar(navController = rememberNavController())
}


