package shop.provider

import shop.model.Product

interface ProductsProvider {
  fun loadDrugsFromFile(): Product
}