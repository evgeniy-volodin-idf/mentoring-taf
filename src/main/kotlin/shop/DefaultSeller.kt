package shop

import shop.model.Drug

class DefaultSeller : Seller {
  private val warehouse: Warehouse = DefaultWarehouse()
  private val cart: Cart = DefaultCart()

  override fun printListOfDrugs() {
    warehouse.showActualDrugs()
  }

  override fun selectDrug() {
    println("")
    println("Select drug from list above. Enter Drug name and press Enter")
    var selectedDrug = readLineFromConsole()
    while (!warehouse.isDrugExist(selectedDrug)) {
      println("Input is incorrect. Try again")
      selectedDrug = readLineFromConsole()
    }
    println("Enter Quantity and press Enter")
    var selectedQuantity = readLineFromConsole()
    while (!warehouse.isQuantityExist(selectedDrug, selectedQuantity)) {
      println("Easy, Tiger! Select correct quantity")
      selectedQuantity = readLineFromConsole()
    }
    val selectedQuantityInt = selectedQuantity.toInt()
    val drug: Drug = warehouse.getSelectedDrug(selectedDrug, selectedQuantityInt)
    cart.addPosition(drug)
    println("$drug is added to Cart")
  }

  private fun readLineFromConsole(): String {
    return readLine()!!.toString().toUpperCase()
  }
}
