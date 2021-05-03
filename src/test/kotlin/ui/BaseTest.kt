package ui

import core.config.FileType
import core.config.YamlConfig
import core.configProvider.ApplicationConfigProvider
import org.junit.jupiter.api.AfterEach
import ui.driver.WebDriverSingleton

open class BaseTest {
  private val appConfig: ApplicationConfigProvider = YamlConfig()

  val url = appConfig.getConfig(FileType.YAML).host
  val user = appConfig.getConfig(FileType.YAML).authUser.user
  val pass = appConfig.getConfig(FileType.YAML).authUser.pass

  @AfterEach
  fun quitDriver(){
    WebDriverSingleton.getWebDriverInstance().quit()
  }
}