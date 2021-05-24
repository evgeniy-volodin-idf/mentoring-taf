package db

import java.sql.ResultSet

object Consumer {
  val resultsFirstRowToMap: (ResultSet) -> Map<String, Any?> = {
    val metaData = it.metaData
    val columnsCount = metaData.columnCount
    val firstRowSelectData = HashMap<String, Any?>(columnsCount)
    if (it.next()) {
      for (column in 1..columnsCount) {
        firstRowSelectData[metaData.getColumnName(column)] = it.getObject(column)
      }
    }
    firstRowSelectData
  }

  val resultsAllRowsToList: (ResultSet) -> List<Map<String, Any?>> = {
    val metaData = it.metaData
    val columnCount = metaData.columnCount
    val allRowsSelectedData = ArrayList<HashMap<String, Any?>>()
    while (it.next()) {
      val firstRowSelectedData = HashMap<String, Any?>(columnCount)
      for (column in 1..columnCount) {
        firstRowSelectedData[metaData.getColumnName(column)] = it.getObject(column)
      }
      allRowsSelectedData.add(firstRowSelectedData)
    }
    allRowsSelectedData
  }
}