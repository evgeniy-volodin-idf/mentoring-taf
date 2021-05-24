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
                    WHERE id = ?""", id
    )
    dbClient.selectOneRow(
      query
    ).apply {
      Assertions.assertEquals(expectedEmail, get("email"), "Incorrect email for $id")
    }
  }

  @Test
  fun selectMultipleRowsFromAccountTable() {
    val roleId = 60
    val query: SqlQuery = sqlQuery(
      """SELECT *
                    FROM ${config.dbUserAccountTable}
                    WHERE role_id = ?""", roleId
    )
    dbClient.selectAllRows(query).apply {
      Assertions.assertTrue(this.size > 1, "Single row in result")
    }
  }
}