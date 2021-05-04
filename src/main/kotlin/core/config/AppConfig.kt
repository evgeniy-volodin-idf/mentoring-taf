package core.config

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties("junkConfig")
data class AppConfig(
  val authUser: AuthorisationUser,
  @JsonProperty("app_host")
  val host: String,
  val oneMoreField: String = "One more field",
  val landingPageEndpoint: String
) {
  fun getBaseUrlWithBasicAuthentication() = "https://${authUser.user}:${authUser.pass}@$host$landingPageEndpoint"
}

data class AuthorisationUser(
  val user: String,
  val pass: String
)
