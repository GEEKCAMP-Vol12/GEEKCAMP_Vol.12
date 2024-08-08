package screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.ListItem
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
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
            modifier = Modifier.padding(it).padding(16.dp)
        ) {
            Box(
                modifier = Modifier.padding(bottom = 32.dp)
            ){
                Column() {
                    Text("平均睡眠時間：",textAlign = TextAlign.Left,
                        modifier = Modifier.fillMaxWidth(),
                        style = TextStyle(fontSize = 20.sp))
                    Text("合計カフェイン摂取量：",
                        textAlign = TextAlign.Left,
                        modifier = Modifier.fillMaxWidth(),
                        style = TextStyle(fontSize = 20.sp))

                }
            }

            Text("不健康ランキング",
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp),
                style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 24.sp))

            LazyColumn(modifier = Modifier.padding(bottom = 32.dp)) {
                items(listOf("1","2","3","4","5")){
                    RankingItem()

                }
            }
            Text("自分の順位",
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp),
                style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 24.sp))
            RankingItem()
        }
}}

@Composable
fun RankingItem() {
    Card(border = BorderStroke(width = 2.dp, color = Color.Black), modifier = Modifier.padding(bottom = 4.dp))
    {
        Row(modifier = Modifier.padding(8.dp)){
            Text("１", modifier = Modifier.weight(1f),style = TextStyle(fontSize = 24.sp), textAlign = TextAlign.Center)
            Text("名前", modifier = Modifier.weight(4f),style = TextStyle(fontSize = 24.sp))
            Text("123ポイント",modifier = Modifier.weight(4f),style = TextStyle(fontSize = 24.sp))
        }
    }
}

@Preview
@Composable
fun RankingScreenPreview() {
    RankingScreen(navController = rememberNavController())
}
