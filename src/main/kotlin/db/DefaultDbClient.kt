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
    val dbUrl = "jdbc:mysql://${config.dbHost}:${config.dbPort}/${config.dbEnv}"
    if (session == null) {
      session = session(
        url = dbUrl,
        user = config.dbUser,
        password = config.dbPassword
      )
    }
    logger().info(
      """Session created with parameters: 
       url - $dbUrl
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