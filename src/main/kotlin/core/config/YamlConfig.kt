package core.config

import core.configProvider.ApplicationConfigProvider

class YamlConfig : ApplicationConfigProvider {

  override fun getConfig(): AppConfig {
    return convertFileToObject(FileType.YAML.filePath)
  }
}
