package ui.driver

object DriverManager {
  fun configureDriver(driverConfig: DriverConfig = DefaultDriverConfigProvider().getConfig()){
    setDriverFactory(driverConfig).configureDriver()
  }

  private fun setDriverFactory(driverConfig: DriverConfig = DefaultDriverConfigProvider().getConfig()): DefaultDriverFactory {
    return when (driverConfig.driverType) {
      DriverType.REMOTE -> RemoteDriverFactory(driverConfig)
      else -> driverSelector(driverConfig)
    }
  }

  fun driverSelector(driverConfig: DriverConfig): DefaultDriverFactory {
    return when (driverConfig.browser) {
      BrowserType.CHROME -> ChromeDriverFactory(driverConfig)
      BrowserType.FIREFOX -> FirefoxDriverFactory(driverConfig)
    }
  }
}