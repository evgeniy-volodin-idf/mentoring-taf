package shop

import shop.model.Drug

class DefaultCart : Cart {
 private var listOfOrderedPosition: MutableList<Drug> = mutableListOf()

  override fun addPosition(drug: Drug) {
    listOfOrderedPosition.add(drug)
  }

  override fun pay() {
    TODO("Not yet implemented")
  }

  override fun printReceipt() {
    TODO("Not yet implemented")
  }
}