package cbl.tools.compose.horizontaldatatable

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Divider
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * [HorizontalDataTable] is the base table with fixed column and fixed header option.
 * This table consists of two [LazyColumn] with synchronized scrolling.
 *
 * [fixedColumnWidth] is the left hand side fixed part width.
 * [fixedColumnModifier] allows customizing fixed column's [Modifier].
 * [biDirectionTableWidth] is the right hand side bi-direction part width.
 * [biDirectionTableModifier] allows customizing bi-direction columns' [Modifier].
 *
 * [headerHeight] is the fixed header height which is nullable.
 * [fixedHeaders] is the builder for fixed headers' composition which is nullable.
 * To enable fixed header feature requires [headerHeight] and [fixedHeaders] to be non-null and not empty.
 *
 * [elevationColor] is color of the elevation area. Default is #EFEFEFEF.
 *
 * [rowCount] is the total row numbers.
 * [columnCount] is the total column numbers. fixed column number(1) + bi-direction columns numbers(n)
 * [cellHeight] is the cell and row height. If cell has smaller height than the row, cell will center vertically.
 * [cells] is the builder for the cells.
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HorizontalDataTable(
    fixedColumnWidth: Dp,
    fixedColumnModifier: Modifier = Modifier,
    biDirectionTableWidth: Dp,
    biDirectionTableModifier: Modifier = Modifier,
    headerHeight: Dp? = null,
    fixedHeaders: (@Composable (colIndex: Int) -> Unit)? = null,
    elevationColor: Color = Color(0xEFEFEFEF),
    rowCount: Int,
    columnCount: Int,
    cellHeight: Dp,
    cells: @Composable (colIndex: Int, rowIndex: Int) -> Unit,
) {
    val horizontalScrollState = rememberScrollState()

    val lazyFixedColumnState = rememberLazyListState()
    val lazyBiDirectionColumnState = rememberLazyListState()

    ScrollSyncNotification(
        lazyFixedColumnState = lazyFixedColumnState,
        lazyBiDirectionColumnState = lazyBiDirectionColumnState
    )

    CompositionLocalProvider(
        LocalOverscrollConfiguration provides null
    ) {
        Row {
            Column {
                safeLet(headerHeight, fixedHeaders) { headerHeight, fixedHeaders ->
                    Box(
                        contentAlignment = Alignment.BottomCenter
                    ) {
                        Divider(
                            color = elevationColor.apply {
                                alpha.times(lazyFixedColumnState.elevationPortion)
                            },
                            modifier = Modifier
                                .width(fixedColumnWidth)
                                .height(lazyFixedColumnState.elevation),
                        )
                        Box(
                            modifier = Modifier.height(headerHeight),
                            contentAlignment = Alignment.Center,
                        ) {
                            fixedHeaders(0)
                        }
                    }
                }
                LazyColumn(
                    state = lazyFixedColumnState,
                    modifier = fixedColumnModifier
                        .fillMaxHeight()
                        .width(fixedColumnWidth),
                    contentPadding = PaddingValues(0.dp),
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
            }
            Box(
                contentAlignment = Alignment.CenterStart
            ) {
                Divider(
                    color = elevationColor.apply {
                        alpha.times(horizontalScrollState.elevationPortion)
                    },
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(horizontalScrollState.elevation),
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(horizontalScrollState)
                ) {
                    safeLet(headerHeight, fixedHeaders) { headerHeight, fixedHeaders ->
                        Box(
                            contentAlignment = Alignment.BottomCenter,
                        ) {
                            Divider(
                                color = elevationColor.apply {
                                    alpha.times(lazyBiDirectionColumnState.elevationPortion)
                                },
                                modifier = Modifier
                                    .width(biDirectionTableWidth)
                                    .height(lazyBiDirectionColumnState.elevation),
                            )
                            Row(
                                modifier = Modifier
                                    .height(headerHeight)
                                    .width(biDirectionTableWidth)
                            ) {
                                for (colIndex in 1 until columnCount) {
                                    Box(
                                        modifier = Modifier
                                            .height(headerHeight),
                                        contentAlignment = Alignment.Center,
                                    ) {
                                        fixedHeaders(colIndex)
                                    }
                                }
                            }
                        }
                    }
                    LazyColumn(
                        state = lazyBiDirectionColumnState,
                        modifier = biDirectionTableModifier
                            .fillMaxHeight()
                            .width(biDirectionTableWidth),
                        contentPadding = PaddingValues(0.dp),
                        content = {
                            items(count = rowCount) { rowIndex ->
                                Row(
                                    modifier = Modifier
                                        .height(cellHeight)
                                        .width(biDirectionTableWidth)
                                ) {
                                    for (colIndex in 1 until columnCount) {
                                        Box(
                                            modifier = Modifier
                                                .height(cellHeight)
                                                .wrapContentWidth(),
                                            contentAlignment = Alignment.Center,
                                        ) {
                                            cells(colIndex, rowIndex)
                                        }
                                    }
                                }
                            }
                        }
                    )
                }
            }
        }
    }
}

/**
 * [PullToRefreshHorizontalDataTable] includes pull to refresh feature to table.
 * The pull to refresh feature is using Material library default pull refresh feature.
 *
 * [refreshing] indicates refresh status.
 * [onRefresh] is callback for onRefresh state.
 * [refreshState] is to remeber the current refresh state.
 * [indicator] is a composable for refresh indicator
 *
 * [fixedColumnWidth] is the left hand side fixed part width.
 * [fixedColumnModifier] allows customizing fixed column's [Modifier].
 * [biDirectionTableWidth] is the right hand side bi-direction part width.
 * [biDirectionTableModifier] allows customizing bi-direction columns' [Modifier].
 *
 * [headerHeight] is the fixed header height which is nullable.
 * [fixedHeaders] is the builder for fixed headers' composition which is nullable.
 * To enable fixed header feature requires [headerHeight] and [fixedHeaders] to be non-null and not empty.
 *
 * [elevationColor] is color of the elevation area. Default is #EFEFEFEF.
 *
 * [rowCount] is the total row numbers.
 * [columnCount] is the total column numbers. fixed column number(1) + bi-direction columns numbers(n)
 * [cellHeight] is the cell and row height. If cell has smaller height than the row, cell will center vertically.
 * [cells] is the builder for the cells.
 */
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
    headerHeight: Dp? = null,
    fixedHeaders: (@Composable (colIndex: Int) -> Unit)? = null,
    elevationColor: Color = Color(0xEFEFEFEF),
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
            rowCount = rowCount,
            columnCount = columnCount,
            cellHeight = cellHeight,
            cells = cells,
            headerHeight = headerHeight,
            fixedHeaders = fixedHeaders,
            elevationColor = elevationColor,
        )
    }
}

/**
 * [ScrollSyncNotification] is a composable managing the synchronized scrolling state of the two [LazyColumn].
 */
@Composable
fun ScrollSyncNotification(
    lazyFixedColumnState: LazyListState,
    lazyBiDirectionColumnState: LazyListState
) {
    LaunchedEffect(lazyFixedColumnState.firstVisibleItemScrollOffset) {
        if (!lazyBiDirectionColumnState.isScrollInProgress) {

            val rowIndex = lazyFixedColumnState.firstVisibleItemIndex

            lazyBiDirectionColumnState.scrollToItem(
                rowIndex,
                lazyFixedColumnState.firstVisibleItemScrollOffset
            )
        }
    }

    LaunchedEffect(lazyBiDirectionColumnState.firstVisibleItemScrollOffset) {
        if (!lazyFixedColumnState.isScrollInProgress) {

            val rowIndex = lazyBiDirectionColumnState.firstVisibleItemIndex

            lazyFixedColumnState.scrollToItem(
                rowIndex,
                lazyBiDirectionColumnState.firstVisibleItemScrollOffset
            )
        }
    }
}