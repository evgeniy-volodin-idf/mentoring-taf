package shop

import shop.context.ContextHolder
import shop.model.Drug
import shop.model.DrugType

class DefaultWarehouse(
  private val drugsInWarehouse: List<Drug> = ContextHolder.getContext().drugsInWarehouse
) : Warehouse {

  override fun showActualDrugs() {
    drugsInWarehouse.forEach(::println)
  }

  override fun isDrugExists(drugName: String): Boolean {
    if (DrugType.values().find { it.name == drugName } != null) {
      return getDrugByName(drugName) != null
    }
    return false
  }

  override fun isQuantityExist(selectedDrug: String, selectedQuantity: String): Boolean {
    val requiredQuantity = selectedQuantity.toLong()
    return getDrugByName(selectedDrug)!!.quantity >= requiredQuantity
  }

  override fun getSelectedDrug(selectedDrug: String, selectedQuantity: Int): Drug {
    val drug: Drug
    val drugInStock: Drug = getDrugByName(selectedDrug)!!
    drugInStock.apply {
      quantity -= selectedQuantity
    }.also {
      drug = Drug(
        productName = it.productName,
        price = it.price,
        quantity = selectedQuantity,
        dateReceived = it.dateReceived
      )
    }
    return drug
  }

  override fun dismissOrder(listOfDrugs: List<Drug>) {
    listOfDrugs.onEach {
      getDrugByName(it.productName.toString())!!.quantity += it.quantity
    }
  }

  private fun getDrugByName(drugName: String): Drug? {
    return drugsInWarehouse.find { it.productName.name == drugName }
  }
}
