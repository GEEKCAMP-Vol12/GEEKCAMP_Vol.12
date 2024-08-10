package screen


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.material.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import navigation.BottomNavigationBar
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
fun SettingScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    Scaffold(
        modifier = modifier,
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }
    )
    {
        Column (modifier = Modifier
            .background(Color(0xFFF5F4A5))
            .padding(it)
            .padding(16.dp)
            .fillMaxSize()) {
            var check by remember { mutableStateOf(true)}
            var checke by remember { mutableStateOf(true)}
            var checked = false


            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(modifier = Modifier.weight(1f)
                    , text = "記録のリマインド通知を出す"
                    , fontSize = 23 .sp
                )
                Switch(checked = check,
                    onCheckedChange = {
                        check = it
                    },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = Color.Black,
                        uncheckedThumbColor = Color.White,
                        checkedTrackColor = Color(0x1D1E1C).copy(alpha = 0.5f),
                        uncheckedTrackColor = Color(0xDED3A5).copy(alpha = 0.5f)
                    )
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = "ランキングに参加する"
                    , fontSize = 23.sp
                )
                Switch(checked = checke,
                    onCheckedChange = {
                        checke = it
                    },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = Color.Black,
                        uncheckedThumbColor = Color.White,
                        checkedTrackColor = Color(0x1D1E1C).copy(alpha = 0.5f),
                        uncheckedTrackColor = Color(0xDED3A5).copy(alpha = 0.5f)
                    )
                )
            }
        }
    }
}

fun Text(modifier: Modifier, text: String, fontSize: Int) {

}


@Preview
@Composable
fun SettingScreenPreview() {
    SettingScreen(navController = rememberNavController())
}

