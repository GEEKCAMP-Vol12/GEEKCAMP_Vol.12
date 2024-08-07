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
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import healthapp.composeapp.generated.resources.Res
import healthapp.composeapp.generated.resources.character_good
import healthapp.composeapp.generated.resources.room_sunny
import navigation.BottomNavigationBar
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
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
    screenSizeDp: Pair<Float, Float>,
) {
    println("screenSizeDp: $screenSizeDp")
    val charLayoutInfo = calculateCharLayoutInfo(screenSizeDp)
    println(charLayoutInfo)
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
            Column(
                modifier = Modifier
                    .fillMaxSize()
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
                            text = "2024/08/05 ~ 2024/08/11",
                            style = TextStyle(
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )
                        )
                        Text("平均睡眠時間：　8時間")
                        Text("平均カフェイン摂取量：　500mg")
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
                            Text("😀", fontSize = 24.sp)
                        }
                    }
                }

            }
            Image(
                modifier = Modifier
                    .scale(1.0f)
                    .padding(
                        top = charLayoutInfo.topMargin.dp,
                        bottom = charLayoutInfo.bottomMargin.dp,
                    )
                    .width(charLayoutInfo.charSize.first.dp)
                    .height(charLayoutInfo.charSize.second.dp)
                    .align(Alignment.BottomCenter),
                painter = painterResource(Res.drawable.character_good),
                contentScale = ContentScale.Fit,
                contentDescription = "character",
            )
        }

    }
}

data class CharLayoutInfo(
    val charSize: Pair<Float, Float>,
    val topMargin: Int,
    val bottomMargin: Int,
)

fun calculateCharLayoutInfo(
    screenSizeDp: Pair<Float, Float>,
    topUiSize: Int = 220,
): CharLayoutInfo {
    if(screenSizeDp.first == 0f || screenSizeDp.second == 0f) {
        return CharLayoutInfo(
            charSize = Pair(0f, 0f),
            topMargin = 0,
            bottomMargin = 0,
        )
    }
    val availableHeight = screenSizeDp.second - topUiSize
    val topMargin = availableHeight * 0.1f
    val bottomMargin = availableHeight * 0.1f
    val charHeight = availableHeight - topMargin - bottomMargin
    val charWidth = screenSizeDp.first * 0.8f
    return CharLayoutInfo(
        charSize = Pair(charWidth, charHeight),
        topMargin = topMargin.toInt(),
        bottomMargin = bottomMargin.toInt(),
    )
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(navController = rememberNavController())
}
