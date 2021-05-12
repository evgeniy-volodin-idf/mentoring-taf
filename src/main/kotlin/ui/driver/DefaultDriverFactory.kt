package ui.driver

import com.codeborne.selenide.Configuration

abstract class DefaultDriverFactory(protected val driverConfig: DriverConfig) {
  abstract fun configureDriver()

  fun defaultDriverConfig() {
    Configuration.browserSize = driverConfig.windowScreenSize
    Configuration.timeout = driverConfig.selenideWaitElementTimeoutMilliseconds
  }
}