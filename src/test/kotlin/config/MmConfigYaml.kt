package config

import core.config.AppConfig
import core.config.FileType
import core.config.YamlConfig
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class MmConfigYaml {
  @Test
  fun yamlConfigCheck() {
    val config: AppConfig = YamlConfig().getConfig()

    config.apply {
      assertAll(
        {
          Assertions.assertEquals(authUser.pass, "1005")
        },
        {
          Assertions.assertEquals(authUser.user, "moneyman")
        },
        {
          Assertions.assertEquals(host, "qa-delivery-mx-master.moneyman.ru")
        }
      )
    }
  }
}