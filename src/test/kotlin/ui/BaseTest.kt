package ui

import org.junit.jupiter.api.BeforeAll
import ui.driver.DriverManager

open class BaseTest {
  @BeforeAll
  fun globalConfig() {
    DriverManager.setDriverFactory().configureDriver()
  }
}