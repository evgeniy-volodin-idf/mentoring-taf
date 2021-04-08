package core.config

data class AppConfig(
  val authUser: AuthorisationUser
)

data class AuthorisationUser(
  val user: String,
  val pass: String
)
