package cbl.tools.compose.horizontaldatatable

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.PullRefreshState
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HorizontalDataTable(
    fixedColumnWidth: Dp,
    fixedColumnModifier: Modifier = Modifier,
    biDirectionTableWidth: Dp,
    biDirectionTableModifier: Modifier = Modifier,
    biDirectionTableGriCells: GridCells? = null,
    rowCount: Int,
    columnCount: Int,
    cellHeight: Dp,
    cells: @Composable (colIndex: Int, rowIndex: Int) -> Unit,
) {
    val horizontalScrollState = rememberScrollState()

    val lazyColumnState = rememberLazyListState()
    val lazyGridState = rememberLazyGridState()

    ScrollSyncNotification(
        columnCount = columnCount,
        lazyListState = lazyColumnState,
        lazyGridState = lazyGridState
    )

    CompositionLocalProvider(
        LocalOverscrollConfiguration provides null
    ) {
        Row {
            LazyColumn(
                state = lazyColumnState,
                modifier = fixedColumnModifier
                    .width(fixedColumnWidth),
                content = {
                    items(count = rowCount) { rowIndex ->
                        Box(
                            modifier = Modifier
                                .height(cellHeight),
                            contentAlignment = Alignment.Center,
                        ) {
                            cells(0, rowIndex)
                        }
                    }
                }
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(horizontalScrollState)
            ) {
                LazyVerticalGrid(
                    state = lazyGridState,
                    modifier = biDirectionTableModifier
                        .width(biDirectionTableWidth),
                    columns = biDirectionTableGriCells
                        ?: GridCells.Fixed(count = (columnCount - 1)),
                    content = {
                        items(count = rowCount * (columnCount - 1)) { index ->
                            val colIndex = index % (columnCount - 1)
                            val rowIndex = (index - colIndex) / (columnCount - 1)
                            Box(
                                modifier = Modifier.height(cellHeight),
                                contentAlignment = Alignment.Center,
                            ) {
                                cells(colIndex + 1, rowIndex)
                            }
                        }
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PullToRefreshHorizontalDataTable(
    refreshing: Boolean = false,
    onRefresh: () -> Unit,
    refreshState: PullRefreshState = rememberPullRefreshState(
        refreshing = refreshing,
        onRefresh = onRefresh,
        refreshingOffset = 100.dp,
        refreshThreshold = 100.dp
    ),
    indicator: (@Composable () -> Unit)? = null,
    fixedColumnWidth: Dp,
    fixedColumnModifier: Modifier = Modifier,
    biDirectionTableWidth: Dp,
    biDirectionTableModifier: Modifier = Modifier,
    biDirectionTableGriCells: GridCells? = null,
    rowCount: Int,
    columnCount: Int,
    cellHeight: Dp,
    cells: @Composable (colIndex: Int, rowIndex: Int) -> Unit,
) {

    Box(modifier = Modifier.pullRefresh(refreshState)) {
        if (indicator != null) {
            indicator?.invoke()
        } else {
            PullRefreshIndicator(
                refreshing = refreshing,
                state = refreshState,
                modifier = Modifier.align(Alignment.TopCenter)
            )
        }

        HorizontalDataTable(
            fixedColumnWidth = fixedColumnWidth,
            fixedColumnModifier = fixedColumnModifier,
            biDirectionTableWidth = biDirectionTableWidth,
            biDirectionTableModifier = biDirectionTableModifier,
            biDirectionTableGriCells = biDirectionTableGriCells,
            rowCount = rowCount,
            columnCount = columnCount,
            cellHeight = cellHeight,
            cells = cells,
        )
    }
}

@Composable
fun ScrollSyncNotification(
    columnCount: Int,
    lazyListState: LazyListState,
    lazyGridState: LazyGridState
) {
    LaunchedEffect(lazyListState.firstVisibleItemScrollOffset) {
        if (!lazyGridState.isScrollInProgress) {

            val rowIndex = (lazyListState.firstVisibleItemIndex * (columnCount - 1)) + 1

            lazyGridState.scrollToItem(
                rowIndex,
                lazyListState.firstVisibleItemScrollOffset
            )
        }
    }

    LaunchedEffect(lazyGridState.firstVisibleItemScrollOffset) {
        if (!lazyListState.isScrollInProgress) {

            val colIndex = lazyGridState.firstVisibleItemIndex % (columnCount - 1)
            val rowIndex = (lazyGridState.firstVisibleItemIndex - colIndex) / (columnCount - 1)

            lazyListState.scrollToItem(
                rowIndex,
                lazyGridState.firstVisibleItemScrollOffset
            )
        }
    }
}