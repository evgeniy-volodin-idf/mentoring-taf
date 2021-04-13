package shop.provider

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.module.kotlin.KotlinModule
import shop.model.Product

class YamlDataFactory : ProductsProvider {
  private val filePath: String = "shop/drugsBase.yml"

  override fun loadDrugsFromFile(): Product {
    return Thread.currentThread().contextClassLoader.getResourceAsStream(filePath)?.use {
      ObjectMapper(YAMLFactory())
        .registerModule(KotlinModule())
        .readValue(it, Product::class.java)
    } ?: throw IllegalStateException("Could not get Drugs from .yaml file")
  }
}