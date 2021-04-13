package shop

import shop.model.Drug
import shop.model.Product

interface Warehouse {
  fun getDrugs(): Product?

  fun setDrugs()
}