package shop

import shop.model.Drug
import java.io.BufferedReader
import java.io.InputStreamReader

class DefaultSeller : Seller {
  private val listOfDrugs: Warehouse = DefaultWarehouse()

  override fun printListOfDrugs() {
    with(listOfDrugs) {
      setDrugs()
      getDrugs()?.product?.forEach(::println)
    }
  }

  override fun selectDrug() {
    println("")
    println("Select drug from list above. Enter Drug name and press Enter")
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val selectedDrug: Drug = verifyDrugInput(reader)
    println("Enter Quantity and press Enter")
    verifyQuantity(reader, selectedDrug)
    println("")
    println("${selectedDrug.quantity} grams of ${selectedDrug.productName} is added to Cart")
  }

  override fun addDrugToCart() {
    TODO("Not yet implemented")
  }

  private fun verifyDrugInput(input: BufferedReader): Drug {
    var selectedDrug: Drug? = null
    while (null == selectedDrug) {
      val buffer: String = input.readLine().toString().toUpperCase()
      selectedDrug = listOfDrugs.getDrugs()?.product?.find {
        it.productName.toString() == buffer
      }
      if (null == selectedDrug) println("Input is incorrect. Try again")
    }
    return Drug(selectedDrug.productName, selectedDrug.price, quantity = 0)
  }

  private fun verifyQuantity(input: BufferedReader, drug: Drug?) {
    while (0 == drug?.quantity) {
      val quantity: Int = input.readLine().toInt()
      if ((listOfDrugs.getDrugs()?.product?.find {
          it.productName == drug.productName
        }?.quantity?.minus(quantity))!! >= 0)
        drug.quantity = quantity
      else println("Easy, Tiger! Select a little bit less")
    }
  }
}
