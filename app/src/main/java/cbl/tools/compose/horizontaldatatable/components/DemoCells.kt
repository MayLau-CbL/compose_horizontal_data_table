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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


class DemoCells : Cells {
    override fun fixedColumnWidth(): Dp {
        return 100.dp
    }

    override fun biDirectionColumnWidth(): Dp {
        return 500.dp
    }

    override fun totalColumns(): Int {
        return 5
    }

    override fun totalRows(): Int {
        return 100
    }

    @Composable
    override fun FixedHeaders(colIndex: Int) {
        when (colIndex) {
            0 -> {
                Text(
                    modifier = Modifier.width(100.dp),
                    text = "User",
                    style = TextStyle(fontWeight = FontWeight.Bold)
                )
            }
            1 -> {
                Text(
                    modifier = Modifier.width(150.dp),
                    text = "Status",
                    style = TextStyle(fontWeight = FontWeight.Bold)
                )
            }
            2 -> {
                Text(
                    modifier = Modifier.width(150.dp),
                    text = "Phone",
                    style = TextStyle(fontWeight = FontWeight.Bold)
                )
            }
            3 -> {
                Text(
                    modifier = Modifier.width(100.dp),
                    text = "Date",
                    style = TextStyle(fontWeight = FontWeight.Bold)
                )
            }
            else -> {
                Text(
                    modifier = Modifier.width(100.dp),
                    text = "Termination",
                    style = TextStyle(fontWeight = FontWeight.Bold)
                )
            }
        }
    }

    @Composable
    override fun FixedColumnCells(rowIndex: Int) {
        User(rowIndex = rowIndex)
    }

    @Composable
    override fun BiDirectionCells(colIndex: Int, rowIndex: Int) {
        when (colIndex) {
            1 -> {
                Status(rowIndex = rowIndex)
            }
            2 -> {
                Phone()
            }
            3 -> {
                Date()
            }
            else -> {
                Termination()
            }
        }
    }

    @Composable
    fun User(rowIndex: Int) {
        Text(
            modifier = Modifier.width(100.dp),
            text = "user_$rowIndex"
        )
    }

    @Composable
    fun Status(rowIndex: Int) {
        val enable = rowIndex % 2 == 0
        Row(modifier = Modifier.width(150.dp)) {
            Icon(
                Icons.Rounded.Notifications,
                contentDescription = "notifications",
                tint = if (enable) Color.Green else Color.Red
            )
            Text(text = if (enable) "Enabled" else "Disabled")
        }
    }

    @Composable
    fun Phone() {
        Text(modifier = Modifier.width(150.dp), text = "+001 99999999")
    }

    @Composable
    fun Date() {
        Text(modifier = Modifier.width(100.dp), text = "2022-12-12")
    }

    @Composable
    fun Termination() {
        Text(modifier = Modifier.width(100.dp), text = "N/A")
    }
}