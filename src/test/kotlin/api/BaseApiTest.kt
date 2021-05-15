package api

import core.config.AppConfig
import core.config.YamlConfig

abstract class BaseApiTest {
  val config: AppConfig = YamlConfig().getConfig()
}