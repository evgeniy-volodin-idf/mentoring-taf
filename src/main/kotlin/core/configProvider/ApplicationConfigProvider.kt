package core.configProvider

import core.config.AuthorisationUser
import core.config.FileType

interface ApplicationConfigProvider {
  fun getConfig(fileType: FileType): AuthorisationUser
}