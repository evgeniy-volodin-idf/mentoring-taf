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

  override fun isDrugExist(drugName: String): Boolean {
    var bool = false
    if (DrugType.values().find { it.name == drugName } != null) {
      val drugType = DrugType.valueOf(drugName)
      bool = drugsInWarehouse.find { it.productName == drugType } != null
    }
    return bool
  }

  override fun isQuantityExist(selectedDrug: String, selectedQuantity: String): Boolean {
    return drugsInWarehouse.find { it.productName == DrugType.valueOf(selectedDrug) }!!.quantity >= selectedQuantity
    .toInt()
  }

  override fun getSelectedDrug(selectedDrug: String, selectedQuantity: Int): Drug {
    val drugType = DrugType.valueOf(selectedDrug)
    val drug: Drug
    val drugInStock: Drug = drugsInWarehouse.find { it.productName == drugType }!!
    drugInStock.apply {
      quantity -= selectedQuantity
    }.also {
      drug = Drug(productName = it.productName, price = it.price, quantity = selectedQuantity)
    }
    return drug
  }
}
