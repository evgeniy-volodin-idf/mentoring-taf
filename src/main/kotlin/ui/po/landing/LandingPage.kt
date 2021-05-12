package ui.po.landing

import ui.po.AbstractPage

class LandingPage : AbstractPage() {
  override val pageEndpoint = config.landingPageEndpoint

  val calculatorBlock: CalculatorBlock by lazy { CalculatorBlock() }
}