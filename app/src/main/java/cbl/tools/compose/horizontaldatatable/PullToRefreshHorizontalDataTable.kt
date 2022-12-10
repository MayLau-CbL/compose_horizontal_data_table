package cbl.tools.compose.horizontaldatatable

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.unit.dp
import cbl.tools.compose.horizontaldatatable.components.Cells
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PullToRefreshHorizontalDataTablePage(cells: Cells) {
    val isRefresh = remember {
        mutableStateOf(false)
    }
    val composableScope = rememberCoroutineScope()
    PullToRefreshHorizontalDataTable(
        refreshing = isRefresh.value,
        onRefresh = {
            composableScope.launch {
                isRefresh.value = true
                delay(2000)
                isRefresh.value = false
            }
        },
        fixedColumnWidth = cells.fixedColumnWidth(),
        biDirectionTableWidth = cells.biDirectionColumnWidth(),
        biDirectionTableGriCells = GridCells.Fixed(cells.totalColumns() - 1),
        columnCount = cells.totalColumns(),
        rowCount = cells.totalRows(),
        cellHeight = 52.dp,
    ) { colIndex, rowIndex ->
        if (colIndex == 0) {
            cells.fixedColumnCells(rowIndex = rowIndex)
        } else {
            cells.BiDirectionCells(colIndex = colIndex, rowIndex = rowIndex)
        }
    }
}