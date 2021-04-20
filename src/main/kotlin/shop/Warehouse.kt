package shop

import shop.model.Drug

interface Warehouse {

  fun showActualDrugs()

  fun isDrugExists(drugName: String): Boolean

  fun getSelectedDrug(selectedDrug: String, selectedQuantity: Int): Drug

  fun isQuantityExist(selectedDrug: String, selectedQuantity: String): Boolean

  fun dismissOrder(listOfDrugs: List<Drug>)
}