package screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import navigation.BottomNavigationBar
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun RankingScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    Scaffold(
        modifier = modifier,
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }
    ) {
        Column(
            modifier = Modifier
                .background(color = Color(0xFFF5F4A5))
                .padding(it)
                .padding(16.dp)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Box(
                modifier = Modifier.padding(bottom = 32.dp)
            ){
                Column (
                    modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.White)
                    .fillMaxWidth()
                    .padding(8.dp),){
                    Text(
                        modifier = Modifier.padding(bottom = 8.dp),
                        text = "2024/08/11 ~ 2024/08/18",
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Text("平均睡眠時間：　7.5時間")
                    Text("平均カフェイン摂取量：　375mg")
                }
            }

            Text("不健康ランキング",
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp),
                style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 24.sp))

            LazyColumn(modifier = Modifier.padding(bottom = 32.dp).height(300.dp)) {
                items(demoRankingData){
                    RankingItem(it.rank, it.name, it.point)
                }
            }
            Text("自分の順位",
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp),
                style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 24.sp)
            )
            RankingItem(1, "不健康太郎", 1000)
        }
    }
}

data class RankingData(
    val rank: Int,
    val name: String,
    val point: Int
)

val demoRankingData = listOf(
    RankingData(
        rank = 1,
        name = "不健康太郎",
        point = 1000
    ),
    RankingData(
        rank = 2,
        name = "山田花子",
        point = 820
    ),
    RankingData(
        rank = 3,
        name = "田中太郎",
        point = 800
    ),
    RankingData(
        rank = 4,
        name = "佐藤花子",
        point = 750
    ),
    RankingData(
        rank = 5,
        name = "鈴木太郎",
        point = 700
    )
)

@Composable
fun RankingItem(rank: Int, name: String, point: Int) {
    Card(border = BorderStroke(width = 2.dp, color = Color.Black), modifier = Modifier.padding(bottom = 4.dp))
    {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ){
            Text("$rank", modifier = Modifier.padding(end = 8.dp),style = TextStyle(fontSize = 24.sp), textAlign = TextAlign.Center)
            Text(name, modifier = Modifier.weight(1f),style = TextStyle(fontSize = 24.sp))
            Text("${point}ポイント",modifier = Modifier,style = TextStyle(fontSize = 24.sp))
        }
    }
}

@Preview
@Composable
fun RankingScreenPreview() {
    RankingScreen(navController = rememberNavController())
}
