package core.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.module.kotlin.KotlinModule
import core.configProvider.ApplicationConfigProvider

class YamlConfig : ApplicationConfigProvider {
  override fun getConfig(fileType: FileType): AuthorisationUser {
    return Thread.currentThread().contextClassLoader.getResourceAsStream(fileType.filePath)?.use {
      ObjectMapper(YAMLFactory())
        .registerModule(KotlinModule())
        .readValue(it, AuthorisationUser::class.java)
    } ?: throw IllegalStateException("Could not get Application Config object")
  }
}
