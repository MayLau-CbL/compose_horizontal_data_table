package cbl.tools.compose.horizontaldatatable

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import cbl.tools.compose.horizontaldatatable.components.Cells

@Composable
fun SimpleHorizontalDataTablePage(cells: Cells) {
    HorizontalDataTable(
        headerHeight = 52.dp,
        fixedHeaders = { colIndex ->
            cells.FixedHeaders(colIndex = colIndex)
        },
        fixedColumnWidth = cells.fixedColumnWidth(),
        biDirectionTableWidth = cells.biDirectionColumnWidth(),
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