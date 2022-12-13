package cbl.tools.compose.horizontaldatatable.refresh

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp

/**
 * RefreshInfo
 *
 * @param refreshing A boolean representing whether a refresh is currently occurring.
 * @param onRefresh The function to be called to trigger a refresh.
 * @param refreshThreshold The threshold below which, if a release
 * occurs, [onRefresh] will be called.
 * @param refreshingOffset The offset at which the indicator will be drawn while refreshing. This
 * offset corresponds to the position of the bottom of the indicator.
 * @param [indicator] is a composable for refresh indicator
 */
data class RefreshInfo @OptIn(ExperimentalMaterialApi::class) constructor(
    var refreshing: Boolean,
    var onRefresh: () -> Unit,
    val refreshingOffset: Dp = PullRefreshDefaults.RefreshingOffset,
    val refreshThreshold: Dp = PullRefreshDefaults.RefreshThreshold,
    val indicator: (@Composable () -> Unit)? = null
)
