package config

import core.config.AuthorisationUser
import core.config.FileType
import core.config.YamlConfig
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class MmConfigYaml {
  @Test
  fun jsonConfigCheck() {
    val config: AuthorisationUser = YamlConfig().getConfig(FileType.YAML)

    config.apply {
      assertAll(
        {
          Assertions.assertEquals(pass, "1005")
        },
        {
          Assertions.assertEquals(user, "moneyman")

        })
    }
  }
}