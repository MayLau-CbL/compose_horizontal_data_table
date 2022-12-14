package cbl.tools.compose.horizontaldatatable.example

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.unit.dp
import cbl.tools.compose.horizontaldatatable.HorizontalDataTable
import cbl.tools.compose.horizontaldatatable.PullToRefreshHorizontalDataTable
import cbl.tools.compose.horizontaldatatable.example.components.Cells
import cbl.tools.compose.horizontaldatatable.refresh.RefreshInfo
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PullToRefreshHorizontalDataTablePage(cells: Cells) {
    val isRefresh = remember {
        mutableStateOf(false)
    }
    val composableScope = rememberCoroutineScope()
    HorizontalDataTable(
        refreshInfo = RefreshInfo(
            refreshing = isRefresh.value,
            onRefresh = {
                composableScope.launch {
                    isRefresh.value = true
                    delay(2000)
                    isRefresh.value = false
                }
            },
        ),
        fixedColumnWidth = cells.fixedColumnWidth(),
        biDirectionTableWidth = cells.biDirectionColumnWidth(),
        headerHeight = 52.dp,
        fixedHeaders = { colIndex ->
            cells.FixedHeaders(colIndex = colIndex)
        },
        columnCount = cells.totalColumns(),
        rowCount = cells.totalRows(),
        cellHeight = 52.dp,
    ) { colIndex, rowIndex ->
        if (colIndex == 0) {
            cells.FixedColumnCells(rowIndex = rowIndex)
        } else {
            cells.BiDirectionCells(colIndex = colIndex, rowIndex = rowIndex)
        }
    }
}