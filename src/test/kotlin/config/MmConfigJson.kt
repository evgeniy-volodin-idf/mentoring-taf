package config

import core.config.AuthorisationUser
import core.config.FileType
import core.config.JsonConfig
import core.configProvider.ApplicationConfigProvider
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class MmConfigJson {
  @Test
  fun jsonConfigCheck() {
    val config: AuthorisationUser = JsonConfig().getConfig(FileType.JSON)

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