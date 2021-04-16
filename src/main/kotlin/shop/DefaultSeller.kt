package shop

import shop.model.Drug

class DefaultSeller : Seller {
  private val warehouse: Warehouse = DefaultWarehouse()
  private val cart: Cart = DefaultCart()

  override fun printListOfDrugs() {
    warehouse.showActualDrugs()
  }

  override fun selectDrug() {
    printlnYellow("Select drug from list above. Enter Drug name and press Enter")
    var selectedDrug = readLineFromConsole()
    while (!warehouse.isDrugExists(selectedDrug)) {
      printlnRed("Keep calm dude, try input correct name")
      selectedDrug = readLineFromConsole()
    }
    printlnYellow("Enter Quantity and press Enter")
    var selectedQuantity = readLineFromConsole()
    while (!warehouse.isQuantityExist(selectedDrug, selectedQuantity)) {
      printlnRed("Easy, Tiger! Select correct quantity")
      selectedQuantity = readLineFromConsole()
    }
    val selectedQuantityInt = selectedQuantity.toInt()
    val drug: Drug = warehouse.getSelectedDrug(selectedDrug, selectedQuantityInt)
    cart.addPosition(drug)
    println("$drug is added to Cart")
  }

  override fun confirmOrder() {
    println("Cart:")
    cart.printCart()
    printlnYellow("Confirm payment. Type YES to pay; NO to cancel payment")
    var count = 0
    var choice = "NO"
    while (choice == "NO" && count < 3) {
      choice = readLineFromConsole()
      if (choice == "NO" && count == 0) {
        printlnRed("Are you sure man? It's are good shit. Type YES to pay; NO to cancel payment")
      }
      if (choice == "NO" && count == 1) {
        printlnRed("Hey bro! Do you respect me? Type YES to respect; NO (not recommended) $")
      }
      if (choice == "NO" && count == 2) {
        printlnRed("It's a big mistake! Order is canceled")
        warehouse.dismissOrder(cart)
        warehouse.showActualDrugs()
        cart.clearCart()
      }
      count++
    }
    if (choice == "YES") {
      cart.addToSoldDrugs()
      cart.printReceipt()
      cart.clearCart()
      printlnYellow("Order is payed. Good luck, have fun!!!")
    }
  }

  private fun readLineFromConsole(): String {
    return readLine()!!.toString().toUpperCase()
  }
}
