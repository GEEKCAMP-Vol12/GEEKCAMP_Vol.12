import androidx.compose.ui.window.ComposeUIViewController
import di.initKoin
import org.koin.compose.koinInject
import viewmodel.HealthAppViewModel

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) {
    val healthAppViewModel: HealthAppViewModel = koinInject()
    App(healthAppViewModel)
}
