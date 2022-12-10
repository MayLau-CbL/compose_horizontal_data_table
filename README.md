# compose_horizontal_data_table

An Android Compose that create a horizontal table with fixed first column.

This is inspired by the [flutter_horizontal_data_table](https://github.com/MayLau-CbL/flutter_horizontal_data_table) which using Android Compose to implement the table. This kind of table should be able to implement via a recycler view. The reason I make this aims to ease the development with pure compose UI implementation. 

## Usage
This shows Widget's full customizations:
### Simple Table
```kotlin
HorizontalDataTable(
    fixedColumnWidth: Dp,
    fixedColumnModifier: Modifier = Modifier,
    biDirectionTableWidth: Dp,
    biDirectionTableModifier: Modifier = Modifier,
    headerHeight: Dp? = null,
    fixedHeaders: (@Composable (colIndex: Int) -> Unit)? = null,
    elevationColor: Color = Color(0xEFEFEFEF),
    rowCount: Int,
    columnCount: Int,
    cellHeight: Dp,
    cells: @Composable (colIndex: Int, rowIndex: Int) -> Unit,
)
```

### With Pull to Refresh Function
```kotlin
PullToRefreshHorizontalDataTable(
    refreshing: Boolean = false,
    onRefresh: () -> Unit,
    refreshState: PullRefreshState = rememberPullRefreshState(
        refreshing = refreshing,
        onRefresh = onRefresh,
        refreshingOffset = 100.dp,
        refreshThreshold = 100.dp
    ),
    indicator: (@Composable () -> Unit)? = null,
    fixedColumnWidth: Dp,
    fixedColumnModifier: Modifier = Modifier,
    biDirectionTableWidth: Dp,
    biDirectionTableModifier: Modifier = Modifier,
    headerHeight: Dp? = null,
    fixedHeaders: (@Composable (colIndex: Int) -> Unit)? = null,
    elevationColor: Color = Color(0xEFEFEFEF),
    rowCount: Int,
    columnCount: Int,
    cellHeight: Dp,
    cells: @Composable (colIndex: Int, rowIndex: Int) -> Unit,
)
```

## Example

<img src="compose_horizontal_data_table.gif" width="250"/>

## License

MIT