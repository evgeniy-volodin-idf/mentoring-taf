package ui.driver

import core.config.convertFileToObject
import java.lang.System.getProperty

class DefaultDriverConfigProvider : DriverConfigProvider {
  private val filePath: String = "driver/driverConfig.yaml"

  override fun getConfig(): DriverConfig {
    val driverConfig: DriverConfig = convertFileToObject(filePath)
    getProperty("driverType")?.also {
      driverConfig.driverType = DriverType.valueOf(it.uppercase())
    }
    return driverConfig
  }
}