import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import healthapp.composeapp.MainNavHost
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        MainNavHost()
    }
}
