package api

import core.config.AppConfig
import core.config.YamlConfig

open class BaseApiTest {
  val config: AppConfig = YamlConfig().getConfig()
}