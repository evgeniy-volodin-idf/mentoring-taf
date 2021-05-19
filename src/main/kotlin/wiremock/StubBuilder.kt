package wiremock

import com.github.tomakehurst.wiremock.client.MappingBuilder
import com.github.tomakehurst.wiremock.client.WireMock.aResponse
import com.github.tomakehurst.wiremock.client.WireMock.get
import com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo

object StubBuilder {
  fun configureStubMapping(mock: Mock): MappingBuilder = get(urlEqualTo(mock.endpoint)).willReturn(
    aResponse().withStatus(mock.code)
  )
}