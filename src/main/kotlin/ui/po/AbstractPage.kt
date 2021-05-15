package ui.po

import com.codeborne.selenide.Selenide
import core.config.AppConfig
import core.config.YamlConfig

abstract class AbstractPage(
  val config: AppConfig = YamlConfig().getConfig()
) {
  abstract val pageEndpoint: String

  fun openPage() {
    Selenide.open("${config.getBaseUrlWithBasicAuthentication()}$pageEndpoint")
  }
}