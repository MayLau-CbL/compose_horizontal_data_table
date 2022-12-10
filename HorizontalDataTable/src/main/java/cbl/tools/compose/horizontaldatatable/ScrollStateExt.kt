package cbl.tools.compose.horizontaldatatable

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.AppBarDefaults
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

val LazyListState.elevation: Dp
    get() = if (firstVisibleItemIndex == 0) {
        minOf(firstVisibleItemScrollOffset.toFloat().dp, AppBarDefaults.TopAppBarElevation)
    } else {
        AppBarDefaults.TopAppBarElevation
    }

val LazyListState.elevationPortion: Float
    get() = elevation.div(AppBarDefaults.TopAppBarElevation)

val ScrollState.elevation: Dp
    get() = if (value > 0) {
        minOf(value.toFloat().dp, AppBarDefaults.TopAppBarElevation)
    } else {
        0.dp
    }

val ScrollState.elevationPortion: Float
    get() = elevation.div(AppBarDefaults.TopAppBarElevation)