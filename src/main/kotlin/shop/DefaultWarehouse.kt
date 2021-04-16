package shop

import shop.model.Drug
import shop.model.DrugType
import shop.provider.WarehouseProvider

class DefaultWarehouse : Warehouse {
  private var drugsInWarehouse: List<Drug> = WarehouseProvider().loadDrugsFromFile()

  override fun getDrugs(): List<Drug> {
    return drugsInWarehouse
  }

  override fun showActualDrugs() {
    drugsInWarehouse.forEach {
      println(it)
    }
  }

  override fun isDrugExists(drugName: String): Boolean {
    if (DrugType.values().find { it.name == drugName } != null) {
      return getDrugByName(drugName) != null
    }
    return false
  }

  override fun isQuantityExist(selectedDrug: String, selectedQuantity: String): Boolean {
    val requiredQuantity = selectedQuantity.toInt()
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

  override fun dismissOrder(cart: Cart) {
    cart.getListOfDrugsInCart().onEach {
      getDrugByName(it.productName.toString())!!.quantity += it.quantity
    }
  }

  private fun getDrugByName(drugName: String): Drug? {
    return drugsInWarehouse.find { it.productName.name == drugName }
  }
}
