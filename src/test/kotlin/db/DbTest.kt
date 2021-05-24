package db

import com.vladsch.kotlin.jdbc.SqlQuery
import com.vladsch.kotlin.jdbc.sqlQuery
import core.config.AppConfig
import core.config.YamlConfig
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DbTest {
  private val config: AppConfig = YamlConfig().getConfig()
  private lateinit var dbClient: DbClient

  @BeforeAll
  fun setupDbClient() {
    dbClient = DefaultDbClient()
    dbClient.getClient()
  }

  @AfterAll
  fun afterTests() {
    dbClient.closeDbConnection()
  }

  @Test
  fun selectOneRowFromAccountTable() {
    val expectedEmail = "qwe@qwe.ewr"
    val id = "4682"
    val query: SqlQuery = sqlQuery(
      """SELECT *
                  FROM ${config.dbUserAccountTable}
                  WHERE id = ?
               """,
      id
    )

    dbClient.selectOneRow(query).also { resultSet: Map<String, Any?> ->
      Assertions.assertEquals(expectedEmail, resultSet["email"], "Incorrect email for $id")
    }
  }

  @Test
  fun selectMultipleRowsFromAccountTable() {
    val roleId = 60
    val query: SqlQuery = sqlQuery(
      """SELECT *
                  FROM ${config.dbUserAccountTable}
                  WHERE role_id = ?
               """,
      roleId
    )

    dbClient.selectAllRows(query).also { resultSet: List<Map<String, Any?>> ->
      Assertions.assertTrue(resultSet.size > 1, "Single row in result")
    }
  }
}