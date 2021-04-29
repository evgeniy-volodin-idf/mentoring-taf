package shop.seller

import shop.context.ContextHolder
import shop.printlnYellow

class DefaultSellingController : SellingController {
  override fun startSelling() {
    val seller: Seller = DefaultSeller()
    printlnYellow("Welcome to Best shop in DarkNet. Press Enter to see our shit")
    var inputMain = readLine()!!
    while (inputMain != "Exit") {
      seller.printListOfDrugs()
      seller.selectDrugAndQuantity()
      printlnYellow(
        """
      Press Enter to continue shopping.
      Type 'Exit' to quit.
      Type 'Shut up and take my money' to switch to order payment process
      """
      )
      inputMain = readLine()!!
      if (inputMain == "Shut up and take my money") {
        seller.confirmOrder()
        println(ContextHolder.getContext().profit)
      }
    }
  }
}