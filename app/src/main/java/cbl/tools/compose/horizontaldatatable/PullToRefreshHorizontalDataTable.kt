package cbl.tools.compose.horizontaldatatable

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PullToRefreshHorizontalDataTablePage() {
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
        fixedColumnWidth = 100.dp,
        biDirectionTableWidth = 500.dp,
        biDirectionTableGriCells = GridCells.Fixed(5),
        columnCount = 6,
        rowCount = 100,
        cellHeight = 36.dp,
    ) { colIndex, rowIndex ->
        Text(
            modifier = Modifier
                .height(36.dp)
                .width(100.dp),
            text = "($colIndex, $rowIndex)"
        )
    }
}