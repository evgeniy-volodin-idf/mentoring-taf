package ui.po

import com.codeborne.selenide.WebDriverRunner.url

class ContactInformationPage : AbstractPage() {
  override val pageEndpoint: String = config.contactInfoPage

  fun isPageUrlValid(): Boolean {
    return url().endsWith(pageEndpoint)
  }
}