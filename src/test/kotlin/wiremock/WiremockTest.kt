package wiremock

import core.config.AppConfig
import core.config.YamlConfig
import http.DefaultHttpClient
import http.HttpClient
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class WiremockTest {
  private lateinit var client: WiremockClient
  private val config: AppConfig = YamlConfig().getConfig()
  private val mock = Mock(endpoint = "/client-area/registration", code = 200)

  @BeforeEach
  fun startWiremockServer() {
    client = DefaultWiremockClient()
    client.server.start()
  }

  @AfterEach
  fun stopWiremockServer() {
    client.server.stop()
  }

  @Test
  fun verifyStubAdded() {
    client.addStub(mock)
    Assertions.assertNotNull(client.getStub(mock), "Stub is not added")
  }

  @Test
  fun verifyStubRemoved() {
    client.addStub(mock)
    client.removeStub(mock)
    Assertions.assertNull(client.getStub(mock), "Stub is not removed")
  }

  @Test
  fun verifyResponseFromStub() {
    val url = "http://${config.wiremockHost}:${config.wiremockPort}${mock.endpoint}"
    val httpClient: HttpClient = DefaultHttpClient()
    val expectedEndpoint = mock.endpoint
    val expectedCode = mock.code
    client.addStub(mock)
    httpClient.get(url).apply {
      assertAll(
        {
          Assertions.assertTrue(url.endsWith(expectedEndpoint), "Stub return incorrect endpoint")
        },
        {
          Assertions.assertEquals(expectedCode, code, "Stub return incorrect code")
        }
      )
    }
  }
}