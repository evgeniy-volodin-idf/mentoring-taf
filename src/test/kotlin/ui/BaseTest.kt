package ui

import org.junit.jupiter.api.BeforeAll
import ui.driver.DefaultDriverConfigProvider
import ui.driver.DriverConfigProvider
import ui.driver.DriverManager

open class BaseTest {
  @BeforeAll
  fun globalConfig() {
    val config: DriverConfigProvider = DefaultDriverConfigProvider()
    DriverManager.setDriverFactory(config.getConfig()).configureDriver()
  }
}