package wiremock

import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.stubbing.StubMapping
import core.config.AppConfig
import core.config.YamlConfig
import org.apache.logging.log4j.kotlin.logger

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
    logger().info("""Stub is added:
      |$mock
    """.trimMargin())
  }

  override fun getStub(mock: Mock): StubMapping? {
    logger().info("""Stub returned:
      |$mock
    """.trimMargin())
    return server.getSingleStubMapping(mock.id)
  }

  override fun getAllStub(): MutableList<StubMapping>? {
    val listOfStubs = server.listAllStubMappings().mappings
    logger().info("""List of all stubs:
      |$listOfStubs
    """.trimMargin())
    return listOfStubs
  }

  override fun removeStub(mock: Mock) {
    server.removeStub(getStub(mock))
    logger().info("""Stub is removed:
      |$mock
    """.trimMargin())
  }
}