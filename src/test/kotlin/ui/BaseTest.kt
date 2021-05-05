package ui

import org.junit.jupiter.api.AfterEach
import ui.driver.WebDriverSingleton

open class BaseTest {
  @AfterEach
  fun quitDriver() {
    WebDriverSingleton.getDriver().quit()
  }
}