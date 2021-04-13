package shop.provider

import shop.Cart
import shop.model.Drug

class DefaultCart : Cart {
  var cart: List<Drug> = arrayListOf()

  override fun addPosition(drug: Drug) {
    TODO("Not yet implemented")
  }

  override fun pay() {
    TODO("Not yet implemented")
  }

  override fun printReceipt() {
    TODO("Not yet implemented")
  }
}