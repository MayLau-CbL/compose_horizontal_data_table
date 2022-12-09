package cbl.tools.compose.horizontaldatatable

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.LocalOverScrollConfiguration
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

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
    val lazyGridState = rememberLazyListState()

    LaunchedEffect(lazyColumnState.firstVisibleItemScrollOffset) {
        if (!lazyGridState.isScrollInProgress) {
            lazyGridState.scrollToItem(
                lazyColumnState.firstVisibleItemIndex,
                lazyColumnState.firstVisibleItemScrollOffset
            )
        }
    }

    LaunchedEffect(lazyGridState.firstVisibleItemScrollOffset) {
        if (!lazyColumnState.isScrollInProgress) {
            lazyColumnState.scrollToItem(
                lazyGridState.firstVisibleItemIndex,
                lazyGridState.firstVisibleItemScrollOffset
            )
        }
    }

    CompositionLocalProvider(
        LocalOverScrollConfiguration provides null
    ) {
        Row {
            LazyColumn(
                state = lazyColumnState,
                modifier = fixedColumnModifier
                    .width(fixedColumnWidth),
                content = {
                    items(count = rowCount) { rowIndex ->
                        Box(modifier = Modifier.height(cellHeight)) {
                            cells(0, rowIndex)
                        }
                    }
                }
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(horizontalScrollState)
            ) {
                LazyVerticalGrid(
                    state = lazyGridState,
                    modifier = biDirectionTableModifier
                        .width(biDirectionTableWidth),
                    cells = biDirectionTableGriCells ?: GridCells.Fixed(count = columnCount),
                    content = {
                        items(count = rowCount * (columnCount - 1)) { index ->
                            val colIndex = index % (columnCount - 1)
                            val rowIndex = (index - colIndex) / (columnCount - 1)
                            Box(modifier = Modifier.height(cellHeight)) {
                                cells(colIndex + 1, rowIndex)
                            }
                        }
                    }
                )
            }
        }
    }
}