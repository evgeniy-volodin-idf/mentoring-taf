package shop

import shop.model.Drug

interface Seller {
  fun printListOfDrugs()

  fun selectDrugAndQuantity()

  fun confirmOrder()
}