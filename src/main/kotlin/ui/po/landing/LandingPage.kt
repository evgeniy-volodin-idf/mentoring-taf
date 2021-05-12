package ui.po.landing

import ui.po.AbstractPage

class LandingPage : AbstractPage() {
  val calculatorBlock: CalculatorBlock by lazy { CalculatorBlock() }

  override val pageEndpoint = config.landingPageEndpoint
}