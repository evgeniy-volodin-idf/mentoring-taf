package ui.po

import core.config.AppConfig
import core.config.FileType
import core.config.YamlConfig
import org.openqa.selenium.WebDriver
import ui.driver.WebDriverSingleton

open class AbstractPage(val driver: WebDriver = WebDriverSingleton.getDriver()) {
  protected val config: AppConfig = YamlConfig().getConfig(FileType.YAML)
}