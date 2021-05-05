package ui.po.landing

import ui.po.AbstractPage

class LandingPage() : AbstractPage() {
  val calculatorBlock: CalculatorBlock by lazy { CalculatorBlock(driver) }

  private val pageEndpoint = config.landingPageEndpoint

  override fun openPage() {
    driver.get("${config.getBaseUrlWithBasicAuthentication()}$pageEndpoint")
  }
}