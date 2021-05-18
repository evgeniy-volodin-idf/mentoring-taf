package wiremock

import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.stubbing.StubMapping
import core.config.AppConfig
import core.config.YamlConfig

class DefaultWiremockClient(
  override var server: WireMockServer = WireMockServer(),
  val config: AppConfig = YamlConfig().getConfig()
) : WiremockClient {

  init {
    configureWiremockServer()
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

  private fun configureWiremockServer() {
    server = WireMockServer(config.port)
  }
}