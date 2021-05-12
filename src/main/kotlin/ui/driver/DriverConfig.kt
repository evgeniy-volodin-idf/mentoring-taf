package ui.driver

data class DriverConfig(
  val browser: BrowserType,
  val windowScreenSize: String,
  var driverType: DriverType,
  val webDriverHost: String,
  val webDriverPort: String,
  val selenideWaitElementTimeoutMilliseconds: Long
)
