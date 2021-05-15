package config

import core.config.AppConfig
import core.config.FileType
import core.config.JsonConfig
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class MmConfigJson {
  @Test
  fun jsonConfigCheck() {
    val config: AppConfig = JsonConfig().getConfig()

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