import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import navigation.MainNavHost
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinContext
import viewmodel.HealthAppViewModel

@Composable
@Preview
fun App(
    healthAppViewModel: HealthAppViewModel,
) {
    KoinContext {
        MaterialTheme {
            MainNavHost(healthAppViewModel = healthAppViewModel)
        }
    }
}
