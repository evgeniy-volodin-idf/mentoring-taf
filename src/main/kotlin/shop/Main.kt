package shop

fun main() {
//  while (true) {
  val seller: Seller = DefaultSeller()
  print("Press Enter to see our stuff")
  while (readLine() != "Shut up and take my money") {
    seller.printListOfDrugs()
    seller.selectDrug()
//    seller.addDrugToCart(cart)
    println("Press Enter to continue shopping. Type 'Shut up and take my money' to switch to order payment process")
  }
//    cart.printReceipt()
//    cart.printReceipt()
//  }
}
