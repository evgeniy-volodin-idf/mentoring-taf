package shop.seller

import shop.cart.Cart
import shop.cart.DefaultCart
import shop.model.Drug
import shop.printlnRed
import shop.printlnYellow
import shop.warehouse.DefaultWarehouse
import shop.warehouse.Warehouse

class DefaultSeller(
  private val cart: Cart = DefaultCart(),
  private val warehouse: Warehouse = DefaultWarehouse()
) : Seller {

  override fun printListOfDrugs() {
    warehouse.showActualDrugs()
  }

  override fun selectDrugAndQuantity() {
    val selectedDrug = selectDrug()
    val selectedQuantityInt = selectQuantity(selectedDrug)
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
    while (choice != "YES" && count < 3) {
      choice = readLineFromConsole()
      when {
        choice == "NO" && count == 0 -> printlnRed("Are you sure man? It's are good shit. Type YES to pay; NO to cancel payment")
        choice == "NO" && count == 1 -> printlnRed("Hey bro! Do you respect me? Type YES to respect; NO (not recommended) $")
        choice == "NO" && count == 2 -> {
          printlnRed("It's a big mistake! Order is canceled")
          warehouse.dismissOrder(cart.getListOfDrugsInCart())
          cart.clearCart()
        }
        else -> printlnRed("Hey Bro, don't understand what you are mumbling there. YES or NO")
      }
      if (choice == "NO") count++
    }
    if (choice == "YES") {
      cart.moveSoldDrugsToContext()
      cart.printReceipt()
      cart.clearCart()
      printlnYellow("Order is payed. Good luck, have fun!!!")
    }
  }

  fun selectDrug(): String {
    printlnYellow("Select drug from list above. Enter Drug name and press Enter")
    var selectedDrug = ""
    var isDrugFound = false
    while (!isDrugFound) {
      selectedDrug = readLineFromConsole()
      isDrugFound = warehouse.isDrugExists(selectedDrug)
      if (!isDrugFound)
        printlnRed("Keep calm dude, try input correct name")
    }
    return selectedDrug
  }

  fun selectQuantity(selectedDrug: String): Int {
    printlnYellow("Enter Quantity and press Enter")
    var selectedQuantity = ""
    var isQuantityCorrect = false
    while (!isQuantityCorrect) {
      selectedQuantity = readLineFromConsole()
      isQuantityCorrect = warehouse.isQuantityExist(selectedDrug, selectedQuantity)
      if (!isQuantityCorrect)
        printlnRed("Easy, Tiger! Select correct quantity")
    }
    return selectedQuantity.toInt()
  }

  private fun readLineFromConsole(): String {
    return readLine()!!.toString().toUpperCase()
  }
}
