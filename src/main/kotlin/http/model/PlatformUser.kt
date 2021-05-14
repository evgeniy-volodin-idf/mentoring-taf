package http.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class PlatformUser {
  var login: String? = null
    get() {
      return field ?: throw IllegalStateException("Login isn't initialized")
    }
  var password: String? = null
    get() {
      return field ?: throw IllegalStateException("Password isn't initialized")
    }
  var role: String? = null
    get() {
      return field ?: throw IllegalStateException("Role isn't initialized")
    }
}