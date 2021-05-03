package ui.po

class ContactInformationPage : AbstractPage() {
  fun verifyURL(): Boolean {
    return driver.currentUrl.endsWith("registration")
  }
}