package ui.driver

class FirefoxDriverFactory(driverConfig: DriverConfig) : DefaultDriverFactory(driverConfig) {
  override fun configureDriver() {
    defaultDriverConfig()
  }
}