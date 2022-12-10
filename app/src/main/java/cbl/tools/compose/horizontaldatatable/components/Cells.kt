package cbl.tools.compose.horizontaldatatable.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp

interface Cells {
    fun fixedColumnWidth(): Dp

    fun biDirectionColumnWidth(): Dp

    fun totalColumns():Int

    fun totalRows():Int

    @Composable
    fun FixedHeaders(colIndex: Int)

    @Composable
    fun FixedColumnCells(rowIndex: Int)

    @Composable
    fun BiDirectionCells(colIndex: Int, rowIndex: Int)
}