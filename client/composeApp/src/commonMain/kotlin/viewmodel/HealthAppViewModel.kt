package viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.demoCaffeineData
import data.demoSleepData
import data.demoUser
import data.model.Caffeine
import data.model.Lce
import data.model.Sleep
import data.model.User
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HealthAppViewModel : ViewModel() {
    private val _appViewState = MutableStateFlow<Lce<AppViewState>>(Lce.Loading)
    val appViewState = _appViewState.asStateFlow()

    fun fetchApplicationData() {
        viewModelScope.launch {
            val userDeferred = async { fetchUserData() }
            val sleepDeferred = async { fetchCurrentWeekSleepData() }
            val caffeineDeferred = async { fetchCurrentWeekCaffeineData() }

            val user = userDeferred.await()
            val sleepData = sleepDeferred.await()
            val caffeineData = caffeineDeferred.await()

            _appViewState.value = Lce.Content(
                AppViewState(
                    currentUserData = user,
                    currentWeekSleepData = sleepData,
                    currentWeekCaffeineData = caffeineData
                )
            )
        }
    }

    private suspend fun fetchCurrentWeekSleepData(): List<Sleep> {
        delay(3_000)
        return demoSleepData.take(7)
    }

    private suspend fun fetchCurrentWeekCaffeineData(): List<Caffeine> {
        delay(3_500)
        return demoCaffeineData.take(7)
    }

    private suspend fun fetchUserData(): User {
        delay(2_000)
        return demoUser
    }
}

data class AppViewState(
    val currentUserData: User? = null,
    val currentWeekSleepData: List<Sleep>? = null,
    val currentWeekCaffeineData: List<Caffeine>? = null,
)
