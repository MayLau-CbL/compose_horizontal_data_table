package cbl.tools.compose.horizontaldatatable.components

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

class TestCells : Cells {
    override fun fixedColumnWidth(): Dp {
        return 100.dp
    }

    override fun biDirectionColumnWidth(): Dp {
        return 500.dp
    }

    override fun totalColumns(): Int {
        return 6
    }

    override fun totalRows(): Int {
        return 100
    }

    @Composable
    override fun fixedColumnCells(rowIndex: Int) {
        Text("(0, $rowIndex)")
    }

    @Composable
    override fun BiDirectionCells(colIndex: Int, rowIndex: Int) {
        Text("($colIndex, $rowIndex)")
    }
}