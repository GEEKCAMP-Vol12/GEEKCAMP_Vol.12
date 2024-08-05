import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import healthapp.composeapp.MainNavHost
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinContext

@Composable
@Preview
fun App() {
    KoinContext {
        MaterialTheme {
            MainNavHost()
        }
    }
}
