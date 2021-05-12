package ui.driver

import com.codeborne.selenide.Configuration

class RemoteDriverFactory(driverConfig: DriverConfig) : DefaultDriverFactory(driverConfig) {
  override fun configureDriver() {
    defaultDriverConfig()
    Configuration.remote = "http://${driverConfig.webDriverHost}:${driverConfig.webDriverPort}/wd/hub"
    DriverManager.driverSelector(driverConfig)
  }
}