package ui.driver

import com.codeborne.selenide.Configuration

class ChromeDriverFactory(driverConfig: DriverConfig) : DefaultDriverFactory(driverConfig) {
  override fun configureDriver() {
    defaultDriverConfig()
    Configuration.browser = BrowserType.CHROME.name.lowercase()
  }
}