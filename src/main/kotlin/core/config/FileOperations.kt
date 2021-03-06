package core.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper

inline fun <reified T> convertFileToObject(filePath: String): T {
  @Suppress("UNCHECKED_CAST")
  return (Thread.currentThread().contextClassLoader.getResourceAsStream(filePath)?.use {
    ObjectMapper(YAMLFactory())
      .registerModule(KotlinModule())
      .readValue(it, T::class.java)
  } as T ?: throw IllegalStateException("Could not get Application Config object"))
}

fun <T> objectToJson(objectToJson: T): String {
  val mapper = jacksonObjectMapper()
  return mapper.writeValueAsString(objectToJson)
}
