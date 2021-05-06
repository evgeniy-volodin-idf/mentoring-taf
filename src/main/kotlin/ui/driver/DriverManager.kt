package ui.driver

object DriverManager {
  fun setDriverFactory(driverConfig: DriverConfig): DefaultDriverFactory {
    return driverSelector(driverConfig)
  }

  private fun driverSelector(driverConfig: DriverConfig): DefaultDriverFactory {
    return when (driverConfig.browser.name) {
      "CHROME" -> ChromeDriverFactory(driverConfig)
      "FIREFOX" -> FirefoxDriverFactory(driverConfig)
      else -> {
        println("Browser is not selected in driver/driverConfig.yaml. Chrome browser will be used by default")
        ChromeDriverFactory(driverConfig)
      }
    }
  }
}