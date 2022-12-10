package cbl.tools.compose.horizontaldatatable

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SimpleHorizontalDataTablePage() {
    HorizontalDataTable(
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