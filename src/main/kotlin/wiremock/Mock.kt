package wiremock

import java.util.*

data class Mock(
  var id: UUID? = null,
  val endpoint: String,
  val code: Int
)