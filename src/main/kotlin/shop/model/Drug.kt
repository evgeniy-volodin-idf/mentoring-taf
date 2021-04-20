package shop.model

import java.time.LocalDateTime

data class Drug(
  val productName: DrugType,
  val price: Int,
  var quantity: Int,
  val dateReceived: LocalDateTime = LocalDateTime.now(),
  var dateSold: String? = null
)

data class Stock(
  val stock: List<Drug>
)