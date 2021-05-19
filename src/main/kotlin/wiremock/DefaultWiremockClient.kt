package wiremock

import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.stubbing.StubMapping
import core.config.AppConfig
import core.config.YamlConfig

class DefaultWiremockClient(
  val config: AppConfig = YamlConfig().getConfig(),
  override var server: WireMockServer = configureWiremockServer(config)
) : WiremockClient {

  companion object {
    private fun configureWiremockServer(config: AppConfig): WireMockServer = WireMockServer(config.wiremockPort)
  }

  override fun addStub(mock: Mock) {
    val stubMapping: StubMapping = server.stubFor(StubBuilder.configureStubMapping(mock))
    mock.id = stubMapping.id
  }

  override fun getStub(mock: Mock): StubMapping? {
    return server.getSingleStubMapping(mock.id)
  }

  override fun getAllStub(): MutableList<StubMapping>? {
    return server.listAllStubMappings().mappings
  }

  override fun removeStub(mock: Mock) {
    server.removeStub(getStub(mock))
  }
}