package shop.provider

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.module.kotlin.KotlinModule
import shop.model.Drug
import shop.model.Stock

class WarehouseProvider : ProductsProvider {
  private val filePath: String = "shop/drugsBase.yml"

  override fun loadDrugsFromFile(): List<Drug> {
    return Thread.currentThread().contextClassLoader.getResourceAsStream(filePath)?.use {
      ObjectMapper(YAMLFactory())
        .registerModule(KotlinModule())
        .readValue(it, Stock::class.java)
    }?.stock ?: throw IllegalStateException("Could not get Drugs from .yaml file")
  }
}