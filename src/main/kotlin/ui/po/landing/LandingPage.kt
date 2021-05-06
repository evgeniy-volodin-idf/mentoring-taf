package ui.po.landing

import com.codeborne.selenide.Selenide.open
import ui.po.AbstractPage

class LandingPage() : AbstractPage() {
  val calculatorBlock: CalculatorBlock by lazy { CalculatorBlock() }

  private val pageEndpoint = config.landingPageEndpoint

  override fun openPage() {
    open("${config.getBaseUrlWithBasicAuthentication()}$pageEndpoint")
  }
}