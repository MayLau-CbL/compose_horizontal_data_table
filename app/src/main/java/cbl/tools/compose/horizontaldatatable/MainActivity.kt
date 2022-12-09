package cbl.tools.compose.horizontaldatatable

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cbl.tools.compose.horizontaldatatable.ui.theme.HorizontalDataTableTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HorizontalDataTableTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    (Scaffold {
                        Box(
                            modifier = Modifier
                                .padding(paddingValues = it)
                        ) {
                            @OptIn(ExperimentalFoundationApi::class)
                            HorizontalDataTable(
                                fixedColumnWidth = 100.dp,
                                biDirectionTableWidth = 500.dp,
                                biDirectionTableGriCells = GridCells.Fixed(5),
                                columnCount = 6,
                                rowCount = 100,
                                cellHeight = 36.dp,
                            ) { colIndex, rowIndex ->
                                Cell(colIndex, rowIndex)
                            }
                        }
                    })
                }
            }
        }
    }

    @Composable
    fun Cell(x: Int, y: Int) {
        Box(
            modifier = Modifier
                .height(36.dp)
                .width(100.dp)
        ) {
            Text("($x, $y)")
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    HorizontalDataTableTheme {
        Greeting("Android")
    }
}