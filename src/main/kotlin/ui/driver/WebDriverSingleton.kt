package ui.driver

import org.openqa.selenium.WebDriver

object WebDriverSingleton {
  private var instance: WebDriver? = null

  fun getDriver(): WebDriver {
    if (instance == null) {
      instance = DriverConfiguration().getWebDriver()
    }
    return instance as WebDriver
  }
}