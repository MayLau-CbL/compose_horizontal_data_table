# compose_horizontal_data_table

[![](https://jitpack.io/v/MayLau-CbL/compose_horizontal_data_table.svg)](https://jitpack.io/#MayLau-CbL/compose_horizontal_data_table)

An Android Compose that create a horizontal table with fixed first column.

This is inspired by the [flutter_horizontal_data_table](https://github.com/MayLau-CbL/flutter_horizontal_data_table) which using Android Compose to implement the table. This kind of table should be able to implement via a recycler view. The reason I make this aims to ease the development with pure compose UI implementation. 

## Usage
This shows widget's full customizations:

```kotlin
HorizontalDataTable(
    refreshInfo: RefreshInfo? = null,
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

## Installation

1. Add repository source
```
    allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

2. Add the dependency
```
    implementation 'com.github.MayLau-CbL:compose_horizontal_data_table:Tag'
```

## Example

<img src="compose_horizontal_data_table.gif" width="250"/>

## License

MIT