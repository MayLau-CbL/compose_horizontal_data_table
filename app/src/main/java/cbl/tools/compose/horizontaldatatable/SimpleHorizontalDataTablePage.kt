package cbl.tools.compose.horizontaldatatable

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import cbl.tools.compose.horizontaldatatable.components.Cells

@Composable
fun SimpleHorizontalDataTablePage(cells: Cells) {
    HorizontalDataTable(
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