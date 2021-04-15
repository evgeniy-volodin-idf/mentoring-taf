package shop.provider

import shop.model.Drug

interface ProductsProvider {
  fun loadDrugsFromFile(): List<Drug>
}