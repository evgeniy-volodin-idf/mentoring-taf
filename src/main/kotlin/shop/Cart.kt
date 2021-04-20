package shop

import shop.model.Drug

interface Cart {
  fun getListOfDrugsInCart(): MutableList<Drug>

  fun addPosition(drug: Drug)

  fun printCart()

  fun printReceipt()

  fun addToSoldDrugs()

  fun clearCart()
}