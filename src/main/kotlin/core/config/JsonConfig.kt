package core.config

import com.fasterxml.jackson.core.JsonFactory
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import core.configProvider.ApplicationConfigProvider

class JsonConfig : ApplicationConfigProvider {
  override fun getConfig(fileType: FileType): AppConfig {
    return Thread.currentThread().contextClassLoader.getResourceAsStream(fileType.filePath)?.use {
      ObjectMapper(JsonFactory())
        .registerModule(KotlinModule())
        .readValue(it, AppConfig::class.java)
    } ?: throw IllegalStateException("Could not get Application Config object")
  }
}
