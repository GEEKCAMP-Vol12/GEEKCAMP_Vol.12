package screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
    ) {
       Box(
           modifier = Modifier
               .padding(it)
               .fillMaxSize(),
           contentAlignment = Alignment.Center
       ) {
           Text("Setting Screen")
       }
    }
}

@Preview
@Composable
fun SettingScreenPreview() {
    SettingScreen(navController = rememberNavController())
}
