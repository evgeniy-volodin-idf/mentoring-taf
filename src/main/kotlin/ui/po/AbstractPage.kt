package ui.po

import com.codeborne.selenide.Selenide.open
import core.config.AppConfig
import core.config.FileType
import core.config.YamlConfig

open class AbstractPage(
  val config: AppConfig = YamlConfig().getConfig(FileType.YAML)
) {
  open fun openPage() {
    open(config.getBaseUrlWithBasicAuthentication())
  }
}