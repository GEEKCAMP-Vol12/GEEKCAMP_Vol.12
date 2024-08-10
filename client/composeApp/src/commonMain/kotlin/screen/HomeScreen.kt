package screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import data.model.Lce
import healthapp.composeapp.generated.resources.Res
import healthapp.composeapp.generated.resources.character_bad
import healthapp.composeapp.generated.resources.character_down
import healthapp.composeapp.generated.resources.character_good
import healthapp.composeapp.generated.resources.character_mid
import healthapp.composeapp.generated.resources.room_sunny
import navigation.BottomNavigationBar
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    homeScreenArg: Lce<HomeScreenArg>,
) {
    val screenDensity = LocalDensity.current.density
    var screenSizeDp by remember {
        mutableStateOf(Pair(0f, 0f))
    }
    Layout(
        modifier = modifier,
        content = {
            HomeScreenContent(
                navController = navController,
                screenSizeDp = screenSizeDp,
                homeScreenArg = homeScreenArg,
            )
        }
    ) { measurables, constraints ->
        screenSizeDp =
            Pair(constraints.maxWidth / screenDensity, constraints.maxHeight / screenDensity)
        layout(constraints.maxWidth, constraints.maxHeight) {
            val placeable = measurables[0].measure(constraints)
            placeable.place(0, 0)
        }
    }
}

@Composable
fun HomeScreenContent(
    modifier: Modifier = Modifier,
    navController: NavController,
    homeScreenArg: Lce<HomeScreenArg>,
    screenSizeDp: Pair<Float, Float>,
) {
    val screenDensity = LocalDensity.current.density
    var topUiHeight by remember {
        mutableStateOf(0)
    }
    val homeScreenLayoutInfo = remember(topUiHeight) {
        val result = calculateHomeScreenLayoutInfo(screenSizeDp, topUiHeight)
        mutableStateOf(result)
    }.value
    val homeScreenData = homeScreenArg.getIfContent() ?: HomeScreenArg.INITIAL_VALUE
    Scaffold(
        modifier = modifier,
        floatingActionButton = {
            FloatingActionButton(
                onClick = {

                },
                content = {
                    Icon(Icons.Rounded.Add, contentDescription = "Add Log")
                },
                backgroundColor = Color(0xFFC1A14E),
                contentColor = Color.White
            )
        },
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }
    ) {
        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
        ) {
            Image(
                modifier = Modifier
                    .blur(3.dp)
                    .fillMaxSize(),
                painter = painterResource(Res.drawable.room_sunny),
                contentDescription = "background",
                contentScale = ContentScale.Crop
            )
            HomeScreenTopUi(
                modifier = Modifier.onGloballyPositioned {
                    topUiHeight = (it.size.height / screenDensity).toInt()
                },
                startDate = homeScreenData.startDate,
                endDate = homeScreenData.endDate,
                averageSleepHour = homeScreenData.averageSleepHour,
                averageCaffeineMg = homeScreenData.averageCaffeineMg,
                healthStatus = homeScreenData.healthStatus,
            )
            if (homeScreenData.healthStatus != HealthStatus.Unknown) {
                Image(
                    modifier = Modifier
                        .scale(1.0f)
                        .padding(
                            top = homeScreenLayoutInfo.topMargin.dp,
                            bottom = homeScreenLayoutInfo.bottomMargin.dp,
                        )
                        .width(homeScreenLayoutInfo.charSize.first.dp)
                        .height(homeScreenLayoutInfo.charSize.second.dp)
                        .align(Alignment.BottomCenter),
                    painter = painterResource(
                        when (homeScreenData.healthStatus) {
                            HealthStatus.Good -> Res.drawable.character_good
                            HealthStatus.Mid -> Res.drawable.character_mid
                            HealthStatus.Bad -> Res.drawable.character_bad
                            HealthStatus.Down -> Res.drawable.character_down
                            else -> Res.drawable.character_good
                        }
                    ),
                    contentScale = ContentScale.Fit,
                    contentDescription = "character",
                )
            }
        }
    }

    if (homeScreenArg is Lce.Loading) {
        Dialog(onDismissRequest = {}) {
            Column(
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.White)
                    .padding(32.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    modifier = Modifier.padding(bottom = 16.dp),
                    text = "読み込み中..."
                )
                CircularProgressIndicator()
            }
        }
    }
}

@Composable
fun HomeScreenTopUi(
    modifier: Modifier,
    startDate: String,
    endDate: String,
    averageSleepHour: String,
    averageCaffeineMg: String,
    healthStatus: HealthStatus,
) {
    Column(
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .padding(16.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color.White)
                .fillMaxWidth()
                .padding(8.dp),
        ) {
            Column {
                Text(
                    modifier = Modifier.padding(bottom = 8.dp),
                    text = "$startDate ~ $endDate",
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
                Text("平均睡眠時間：　${averageSleepHour}時間")
                Text("平均カフェイン摂取量：　${averageCaffeineMg}mg")
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
        ) {
            Row(
                modifier = Modifier
                    .padding(end = 16.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .width(120.dp)
                    .background(Color.White)
                    .padding(horizontal = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("状態：　")
                Box(
                    modifier = Modifier.size(80.dp),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = when (healthStatus) {
                            HealthStatus.Good -> "😀"
                            HealthStatus.Mid -> "🙂"
                            HealthStatus.Bad -> "🥲"
                            HealthStatus.Down -> "🥶"
                            HealthStatus.Unknown -> ""
                        },
                        fontSize = 24.sp
                    )
                }
            }
        }

    }
}

data class HomeScreenArg(
    val startDate: String,
    val endDate: String,
    val averageSleepHour: String,
    val averageCaffeineMg: String,
    val healthStatus: HealthStatus,
) {
    companion object {
        val INITIAL_VALUE = HomeScreenArg(
            startDate = "----",
            endDate = "----",
            averageSleepHour = "----",
            averageCaffeineMg = "----",
            healthStatus = HealthStatus.Unknown,
        )
    }
}

enum class HealthStatus {
    Good,
    Mid,
    Bad,
    Down,
    Unknown,
}

data class HomeScreenLayoutInfo(
    val charSize: Pair<Float, Float> = Pair(0f, 0f),
    val topMargin: Int = 0,
    val bottomMargin: Int = 0,
)

fun calculateHomeScreenLayoutInfo(
    screenSizeDp: Pair<Float, Float>,
    topUiSize: Int = 220,
): HomeScreenLayoutInfo {
    println(topUiSize)
    if (screenSizeDp.first == 0f || screenSizeDp.second == 0f || topUiSize == 0) {
        return HomeScreenLayoutInfo()
    }
    val availableHeight = screenSizeDp.second - topUiSize
    val topMargin = availableHeight * 0.1f
    val bottomMargin = availableHeight * 0.1f
    val charHeight = availableHeight - topMargin - bottomMargin
    val charWidth = screenSizeDp.first * 0.8f
    return HomeScreenLayoutInfo(
        charSize = Pair(charWidth, charHeight),
        topMargin = topMargin.toInt(),
        bottomMargin = bottomMargin.toInt(),
    )
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        homeScreenArg =
        Lce.Content(
            HomeScreenArg(
                startDate = "2024/08/05",
                endDate = "2024/08/11",
                averageSleepHour = "8",
                averageCaffeineMg = "500",
                healthStatus = HealthStatus.Good,
            )
        ),
        navController = rememberNavController()
    )
}
