package wiremock

import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock.aResponse
import com.github.tomakehurst.wiremock.client.WireMock.get
import com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo
import com.github.tomakehurst.wiremock.stubbing.StubMapping

class DefaultWiremockClient(
  override val server: WireMockServer = WiremockClientBuilder.configureWiremockServer()
) : WiremockClient {

  override fun addStub(mock: Mock) {
    server.start()
    val stubMapping = server.stubFor(
      get(urlEqualTo(mock.endpoint)).willReturn(aResponse().withStatus(mock.code))
    )
    mock.id = stubMapping.id
  }

  override fun getStub(mock: Mock): StubMapping? {
    return server.getSingleStubMapping(mock.id)
  }

  override fun getAllStub(): MutableList<StubMapping>? {
    return server.listAllStubMappings().mappings
  }

  override fun removeStub(stubMapping: StubMapping?) {
    server.removeStub(stubMapping)
  }
}