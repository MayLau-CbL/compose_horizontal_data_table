package cbl.tools.compose.horizontaldatatable.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


class DemoCells : Cells {
    override fun fixedColumnWidth(): Dp {
        return 100.dp
    }

    override fun biDirectionColumnWidth(): Dp {
        return 470.dp
    }

    override fun totalColumns(): Int {
        return 5
    }

    override fun totalRows(): Int {
       return 100
    }

    @Composable
    override fun fixedColumnCells(rowIndex: Int) {
        user(rowIndex = rowIndex)
    }

    @Composable
    override fun BiDirectionCells(colIndex: Int, rowIndex: Int) {
        when (colIndex) {
            1 -> {
                status(rowIndex = rowIndex)
            }
            2 -> {
                phone()
            }
            3 -> {
                date()
            }
            else -> {
                termination()
            }
        }
    }

    @Composable
    fun user(rowIndex: Int) {
        Text(
            modifier = Modifier.width(100.dp),
            text = "user_$rowIndex"
        )
    }

    @Composable
    fun status(rowIndex: Int) {
        val enable = rowIndex % 2 == 0
        Row(modifier = Modifier.width(120.dp),) {
            Icon(
                Icons.Rounded.Notifications,
                contentDescription = "notifications",
                tint = if (enable) Color.Green else Color.Red
            )
            Text(text = if (enable) "Enabled" else "Disabled")
        }
    }

    @Composable
    fun phone() {
        Text(modifier = Modifier.width(150.dp),text = "+001 99999999")
    }

    @Composable
    fun date() {
        Text(modifier = Modifier.width(100.dp),text = "2022-12-12")
    }

    @Composable
    fun termination() {
        Text(modifier = Modifier.width(100.dp),text = "N/A")
    }
}