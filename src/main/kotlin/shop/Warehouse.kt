package shop

import shop.model.Drug

interface Warehouse {
  fun getDrugs(): List<Drug>

  fun showActualDrugs()

  fun isDrugExists(drugName: String): Boolean

  fun getSelectedDrug(selectedDrug: String, selectedQuantity: Int): Drug

  fun isQuantityExist(selectedDrug: String, selectedQuantity: String): Boolean

  fun dismissOrder(cart: Cart)
}