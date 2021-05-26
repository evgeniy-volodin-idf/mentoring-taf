package db

import com.vladsch.kotlin.jdbc.Session
import com.vladsch.kotlin.jdbc.SqlQuery
import com.vladsch.kotlin.jdbc.session
import core.config.AppConfig
import core.config.YamlConfig
import org.apache.logging.log4j.kotlin.logger

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
    logger().info(
      """Session created with parameters: 
       url - jdbc:mysql://${config.dbHost}:${config.dbPort}/${config.dbEnv}
       user - ${config.dbUser}
       pass = *****
       """
    )
    return session as Session
  }

  override fun selectOneRow(query: SqlQuery): Map<String, Any?> {
    logger().info(
      """Query to select one row:
      ${query.statement}    
      """
    )
    return getClient().query(query, Consumer.resultsFirstRowToMap)
  }

  override fun selectAllRows(query: SqlQuery): List<Map<String, Any?>> {
    logger().info(
      """Query to select multiple rows:
      ${query.statement}    
      """
    )
    return getClient().query(query, Consumer.resultsAllRowsToList)
  }

  override fun closeDbConnection() {
    if (session != null) {
      getClient().close()
      session = null
    }
    logger().info("DB session is closed")
  }
}