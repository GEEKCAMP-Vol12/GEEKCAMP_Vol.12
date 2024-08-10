package screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
fun MyLogScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    Scaffold(
        modifier = modifier,
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }
    ){
        Column (modifier = Modifier
            .background(color = Color(0xFFF5F4A5))
            .padding(it)
            .padding(16.dp)
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
        ){
            Box(modifier = Modifier.padding(bottom = 32.dp)) {
                Column {
                    var check by remember { mutableStateOf(true) }
                    var checked = false
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

                    Row(modifier = Modifier.padding(bottom = 16.dp),
                        verticalAlignment = Alignment.CenterVertically)
                    {

                        if(check) {
                            Text(
                                "睡眠時間",
                                modifier = Modifier.weight(1f),
                                textAlign = TextAlign.Right,
                                style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold)
                            )
                        }else {
                            Text(
                                "カフェイン摂取量",
                                modifier = Modifier.weight(1f),
                                textAlign = TextAlign.Right,
                                style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold)
                            )
                        }

                        Switch(checked = check,
                            colors = SwitchDefaults.colors(
                                checkedThumbColor = Color(0xFF34342D), // チェック時のサークルの色
                                uncheckedThumbColor = Color(0xFFFFFFFF), // 未チェック時のサークルの色
                                checkedTrackColor = Color.Gray.copy(alpha = 0.5f), // チェック時のトラックの色
                                uncheckedTrackColor = Color.Gray.copy(alpha = 0.5f)), // 未チェック時のトラックの色
                            onCheckedChange = {
                                check = it
                            }
                        )
                        Dropdown()
                    }


                    Box(modifier = Modifier
                        .padding(bottom = 32.dp)
                        .fillMaxWidth()
                        .background(color = Color.Gray)
                        .padding(bottom = 300.dp)
                    ) {
                        Text("")
                    }

                    LazyColumn(
                        modifier = Modifier.height(300.dp)
                    ) {
                        items(listOf("1","2","3","4","5")){
                            if(check) {
                                MyLogSleepItem()

                            }else {
                                MyLogCaffeineItem()

                            }


                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MyLogSleepItem() {
    Card(border = BorderStroke(width = 2.dp, color = Color.Black), modifier = Modifier.padding(bottom = 4.dp))
    {
        Row(modifier = Modifier.padding(8.dp)){
            Text("2024/08/08", modifier = Modifier.weight(1f),style = TextStyle(fontSize = 24.sp))
            Text("10h30m",modifier = Modifier,style = TextStyle(fontSize = 24.sp))
        }
    }
}

@Composable
fun MyLogCaffeineItem() {
    Card(border = BorderStroke(width = 2.dp, color = Color.Black), modifier = Modifier.padding(bottom = 4.dp))
    {
        Row(modifier = Modifier.padding(8.dp)){
            Text("2024/08/08", modifier = Modifier.weight(3f),style = TextStyle(fontSize = 24.sp))
            Text("100mg",modifier = Modifier.weight(1f),style = TextStyle(fontSize = 24.sp))
        }
    }
}

@Composable
fun Dropdown() {
    val options = listOf("日", "週", "月", "年")
    val expanded = remember { mutableStateOf(false) }
    val selectedOptionText = remember { mutableStateOf(options[0]) }

    Box(
        contentAlignment = Alignment.CenterStart,
        modifier = Modifier
            .size(60.dp, 30.dp)
            .clip(RoundedCornerShape(4.dp))
            .background(color = Color(0xFFFFFFFF))
            .border(BorderStroke(1.dp, Color.Black), RoundedCornerShape(4.dp))
            .clickable { expanded.value = !expanded.value }
    ) {
        Text(
            text = selectedOptionText.value,
            modifier = Modifier.padding(start = 10.dp)
        )
        Icon(
            Icons.Filled.ArrowDropDown, "contentDescription",
            Modifier.align(Alignment.CenterEnd)
        )
        DropdownMenu(
            expanded = expanded.value,
            onDismissRequest = { expanded.value = false }
        ) {
            options.forEach { selectionOption ->
                DropdownMenuItem(
                    onClick = {
                        selectedOptionText.value = selectionOption
                        expanded.value = false
                    }
                ) {
                    Text(text = selectionOption)
                }
            }
        }
    }
}

@Preview
@Composable
fun MyLogScreenPreview() {
    MyLogScreen(navController = rememberNavController())
}
