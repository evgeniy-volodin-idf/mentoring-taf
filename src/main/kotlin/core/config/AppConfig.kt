package core.config

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties("junkConfig")
data class AppConfig(
  val authUser: AuthorisationUser,
  @JsonProperty("app_host")
  val host: String,
  val landingPageEndpoint: String,
  val contactInfoPage: String
) {
  fun getBaseUrlWithBasicAuthentication() = "https://${authUser.user}:${authUser.pass}@$host"
}

data class AuthorisationUser(
  val user: String,
  val pass: String
)
