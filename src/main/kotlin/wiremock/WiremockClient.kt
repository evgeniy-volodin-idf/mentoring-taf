package wiremock

import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.stubbing.StubMapping

interface WiremockClient {
  val server: WireMockServer

  fun addStub(mock: Mock)

  fun getStub(mock: Mock): StubMapping?

  fun getAllStub(): MutableList<StubMapping>?

  fun removeStub(mock: Mock)
}