package org.vol12.healthapp

import App
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import viewmodel.HealthAppViewModel

class MainActivity : ComponentActivity() {
    val healthAppViewModel: HealthAppViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
//        val hoge: HealthAppViewModel = inject<HealthAppViewModel>()
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                healthAppViewModel.fetchApplicationData()
            }
        }
        setContent {
            App(healthAppViewModel)
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    val healthAppViewModel = HealthAppViewModel()
    App(healthAppViewModel)
}
