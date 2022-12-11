package cbl.tools.compose.horizontaldatatable

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.AppBarDefaults
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Ext of LazyListState for calculating the scroll elevation
 */
val LazyListState.elevation: Dp
    get() = if (firstVisibleItemIndex == 0) {
        minOf(firstVisibleItemScrollOffset.toFloat().dp, AppBarDefaults.TopAppBarElevation)
    } else {
        AppBarDefaults.TopAppBarElevation
    }

/**
 * Ext of LazyListState for calculating the alpha of elevation color
 */
val LazyListState.elevationPortion: Float
    get() = elevation.div(AppBarDefaults.TopAppBarElevation)

/**
 * Ext of ScrollState for calculating the scroll elevation
 */
val ScrollState.elevation: Dp
    get() = if (value > 0) {
        minOf(value.toFloat().dp, AppBarDefaults.TopAppBarElevation)
    } else {
        0.dp
    }

/**
 * Ext of ScrollState for calculating the alpha of elevation color
 */
val ScrollState.elevationPortion: Float
    get() = elevation.div(AppBarDefaults.TopAppBarElevation)