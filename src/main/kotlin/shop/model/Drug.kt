package shop.model

data class Drug(
  val productName: DrugType,
  val price: Int,
  var quantity: Int,
  var dateReceived: String? = null,
  var dateSold: String? = null
)

data class Stock(
  val stock: List<Drug>
)