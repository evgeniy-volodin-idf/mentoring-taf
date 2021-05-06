package ui.driver

data class DriverConfig(
  val browser: BrowserType,
  val windowScreenSize: String,
  val driverType: String,
  val webDriverHost: String = "",
  val webDriverPort: String = "",
  val selenideWaitElementTimeoutMilliseconds: Long
)
