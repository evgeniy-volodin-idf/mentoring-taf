package shop

import shop.model.Drug

interface Cart {
  fun addPosition(drug: Drug)

  fun pay()

  fun printReceipt()
}