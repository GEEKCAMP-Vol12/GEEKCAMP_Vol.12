package viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import screen.HomeScreenLayoutInfo

class HomeViewModel(
    private val updateUserData: () -> Unit,
) : ViewModel() {
    private val _homeViewState = MutableStateFlow(HomeViewState())
    val homeViewState = _homeViewState.asStateFlow()

    fun openAddLogDialog() {
        _homeViewState.update {
            it.copy(shouldShowAddLogDialog = true)
        }
    }

    fun closeAddLogDialog() {
        _homeViewState.update {
            it.copy(shouldShowAddLogDialog = false)
        }
    }

    fun openReferDialog() {
        if (_homeViewState.value.shouldShowAddLogDialog) {
            _homeViewState.update {
                it.copy(shouldShowReferDialog = true)
            }
        }
    }

    fun closeReferDialog() {
        _homeViewState.update {
            it.copy(shouldShowReferDialog = false)
        }
    }

    fun updateScreenSizeDp(newSize: Pair<Float, Float>) {
        _homeViewState.update {
            it.copy(screenSizeDp = newSize)
        }
    }

    fun updateTopUiHeight(newHeight: Int) {
        calculateHomeScreenLayoutInfo(newHeight)
        _homeViewState.update {
            it.copy(topUiHeight = newHeight)
        }
    }

    fun onSubmitNewLog() {
        updateUserData()
        closeAddLogDialog()
    }

    fun onCancelNewLog() {
        closeAddLogDialog()
    }

    private fun calculateHomeScreenLayoutInfo(topUiSize: Int = 220) {
        if (
            _homeViewState.value.screenSizeDp.first == 0f ||
            _homeViewState.value.screenSizeDp.second == 0f ||
            topUiSize == 0
        ) {
            return
        }
        val availableHeight = _homeViewState.value.screenSizeDp.second - topUiSize
        val topMargin = availableHeight * 0.1f
        val bottomMargin = availableHeight * 0.1f
        val charHeight = availableHeight - topMargin - bottomMargin
        val charWidth = _homeViewState.value.screenSizeDp.first * 0.8f
        _homeViewState.update {
            it.copy(
                homeScreenLayoutInfo = HomeScreenLayoutInfo(
                    charSize = Pair(charWidth, charHeight),
                    topMargin = topMargin.toInt(),
                    bottomMargin = bottomMargin.toInt(),
                )
            )
        }
        println("homeScreenLayoutInfo: ${_homeViewState.value.homeScreenLayoutInfo}")
    }
}

data class HomeViewState(
    val shouldShowAddLogDialog: Boolean = false,
    val shouldShowReferDialog: Boolean = false,
    val screenSizeDp: Pair<Float, Float> = Pair(0f, 0f),
    val topUiHeight: Int = 0,
    val homeScreenLayoutInfo: HomeScreenLayoutInfo = HomeScreenLayoutInfo(),
)
