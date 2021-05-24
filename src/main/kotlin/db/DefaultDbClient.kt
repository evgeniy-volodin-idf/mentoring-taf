package db

import com.vladsch.kotlin.jdbc.Session
import com.vladsch.kotlin.jdbc.SqlQuery
import com.vladsch.kotlin.jdbc.session
import core.config.AppConfig
import core.config.YamlConfig

class DefaultDbClient(val config: AppConfig = YamlConfig().getConfig()) : DbClient {
  private var session: Session? = null

  override fun getClient(): Session {
    if (session == null) {
      session = session(
        url = "jdbc:mysql://${config.dbHost}:${config.dbPort}/${config.dbEnv}",
        user = config.dbUser,
        password = config.dbPassword
      )
    }
    return session as Session
  }

  override fun selectOneRow(query: SqlQuery): Map<String, Any?> {
    return getClient().query(query, Consumer.resultsFirstRowToMap)
  }

  override fun selectAllRows(query: SqlQuery): List<Map<String, Any?>> {
    return getClient().query(query, Consumer.resultsAllRowsToList)
  }

  override fun closeDbConnection() {
    if (session != null) {
      getClient().close()
      session = null
    }
  }
}