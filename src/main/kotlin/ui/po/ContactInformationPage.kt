package ui.po

import com.codeborne.selenide.WebDriverRunner.url

class ContactInformationPage : AbstractPage() {
  fun verifyURL(): Boolean {

    return url().endsWith("registration")
  }
}