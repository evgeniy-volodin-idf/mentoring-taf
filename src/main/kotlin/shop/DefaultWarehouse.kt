package shop

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.module.kotlin.KotlinModule
import shop.model.Product
import shop.provider.ProductsProvider
import shop.provider.YamlDataFactory

class DefaultWarehouse : Warehouse {
  private var listOfAllDrugs: Product? = null

  override fun getDrugs(): Product? {
    return listOfAllDrugs
  }

  override fun setDrugs() {
    val productsProvider: ProductsProvider = YamlDataFactory()
    listOfAllDrugs = productsProvider.loadDrugsFromFile()
  }
}