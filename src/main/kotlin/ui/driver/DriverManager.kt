package ui.driver

object DriverManager {
  fun setDriverFactory(driverConfig: DriverConfig = DefaultDriverConfigProvider().getConfig()): DefaultDriverFactory {
    if (driverConfig.driverType == DriverType.REMOTE)
      return RemoteDriverFactory(driverConfig)
    return driverSelector(driverConfig)
  }

  fun driverSelector(driverConfig: DriverConfig): DefaultDriverFactory {
    return when (driverConfig.browser) {
      BrowserType.CHROME -> ChromeDriverFactory(driverConfig)
      BrowserType.FIREFOX -> FirefoxDriverFactory(driverConfig)
    }
  }
}