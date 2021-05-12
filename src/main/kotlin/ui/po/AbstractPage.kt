package ui.po

import com.codeborne.selenide.Selenide
import core.config.AppConfig
import core.config.FileType
import core.config.YamlConfig

abstract class AbstractPage(
  val config: AppConfig = YamlConfig().getConfig(FileType.YAML)
) {
  abstract val pageEndpoint: String

  fun openPage(){
    Selenide.open("${config.getBaseUrlWithBasicAuthentication()}$pageEndpoint")
  }
}