package ui.po.landing

import ui.po.AbstractPage

class LandingPage() : AbstractPage() {
  val calculatorBlock: CalculatorBlock by lazy { CalculatorBlock(driver)}

  fun openURLWithBasicAuthentication() {
    driver.get(config.getBaseUrlWithBasicAuthentication())
  }
}