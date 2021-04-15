package shop

import shop.model.Drug

interface Cart {
  fun getListOfDrugsInCart(): MutableList<Drug>

  fun addPosition(drug: Drug)

  fun pay()

  fun printReceipt()
}