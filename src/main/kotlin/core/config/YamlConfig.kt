package core.config

import core.configProvider.ApplicationConfigProvider

class YamlConfig : ApplicationConfigProvider {

  override fun getConfig(fileType: FileType): AppConfig {
    return convertFileToObject(fileType.filePath)
  }
}
