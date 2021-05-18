package wiremock

import com.github.tomakehurst.wiremock.WireMockServer

object WiremockClientBuilder {
  fun configureWiremockServer(): WireMockServer {
    return WireMockServer(8089)
  }
}