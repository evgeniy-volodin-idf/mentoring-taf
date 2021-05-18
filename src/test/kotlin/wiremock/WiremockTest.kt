package wiremock

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class WiremockTest {
  private lateinit var client: WiremockClient
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
    client.removeStub(client.getStub(mock))
    Assertions.assertNull(client.getStub(mock), "Stub is not removed")
  }
}