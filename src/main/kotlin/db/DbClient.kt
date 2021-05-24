package db

import com.vladsch.kotlin.jdbc.Session
import com.vladsch.kotlin.jdbc.SqlQuery

interface DbClient {
  fun getClient(): Session

  fun selectOneRow(query: SqlQuery): Map<String, Any?>

  fun selectAllRows(query: SqlQuery): List<Map<String, Any?>>

  fun closeDbConnection()
}