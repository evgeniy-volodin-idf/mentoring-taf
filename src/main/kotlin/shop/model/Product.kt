package shop.model

data class Product(
  val product: List<Drug>
)

data class Drug(
  val productName: DrugType,
  val price: Int,
  var quantity: Int,
  var dateReceived: String? = null,
  var dateSold: String? = null
)