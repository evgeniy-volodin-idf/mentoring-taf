package api

import core.config.toJson
import http.DefaultHttpClient
import http.HttpClient
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class ApiTest : BaseApiTest() {
  private val httpClient: HttpClient = DefaultHttpClient()
  private val headerCookie = "set-cookie"

  @Test
  fun apiGet() {
    val response = httpClient.get("http://${config.host}${config.registration}")
    Assertions.assertNotNull(response.headers(headerCookie), "AuthUser is empty")
  }

  @Test
  fun apiPost() {
    val json = toJson(config.crmPlatformUser)
    println(json)
    val response = httpClient.post("http://${config.hostIp}${config.token}", json)
    println(response)
  }
}