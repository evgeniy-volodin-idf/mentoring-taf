package core.config

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import http.model.PlatformUser

@JsonIgnoreProperties(ignoreUnknown = true)
data class AppConfig(
  val authUser: AuthorisationUser,
  @JsonProperty("app_host")
  val host: String,
  val landingPageEndpoint: String,
  val contactInfoPage: String,
  val hostIp: String,
  val crmPlatformUser: PlatformUser,
  val registration: String,
  val token: String
) {
  fun getBaseUrlWithBasicAuthentication() = "https://${authUser.user}:${authUser.pass}@$host"
}

data class AuthorisationUser(
  val user: String,
  val pass: String
)
