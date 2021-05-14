package core.configProvider

import core.config.AppConfig
import core.config.FileType

interface ApplicationConfigProvider {
  fun getConfig(): AppConfig
}