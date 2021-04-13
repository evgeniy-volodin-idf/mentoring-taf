package shop

fun main() {
//  while (true) {
  val seller: Seller = DefaultSeller()
  seller.printListOfDrugs()
    while (true) {
      seller.selectDrug()
//      seller.addDrugToCart()
    }
//    val cart: Cart
//    cart.pay()
//    cart.printReceipt()
//  }
}
