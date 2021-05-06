package ui.driver

import core.config.convertFileToObject

class DefaultDriverConfigProvider : DriverConfigProvider {
  private val filePath: String = "driver/driverConfig.yaml"

  override fun getConfig(): DriverConfig {
    return convertFileToObject(filePath)
  }
}