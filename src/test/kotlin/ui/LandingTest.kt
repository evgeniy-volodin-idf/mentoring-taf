package ui

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import ui.po.ContactInformationPage
import ui.po.landing.LandingPage

class LandingTest : BaseTest() {
  private val expectedDefaultAmount = "1,500"
  private val expectedDefaultDays = "10"

  @Test
  fun landingPageVerify() {
    val calculator = LandingPage().calculatorBlock
    calculator.openURLWithBasicAuthentication(user, pass, url)
    Assertions.assertTrue(calculator.verifyCalculator(), "Calculator is not displayed")
    Assertions.assertEquals(
      expectedDefaultAmount,
      calculator.verifyDefaultAmount(),
      "Incorrect loan amount is selected by default"
    )
    Assertions.assertEquals(
      expectedDefaultDays,
      calculator.verifyDefaultDays(),
      "Incorrect days are selected by default"
    )
    calculator.updateLoanAmount()
    calculator.updateDays()
    val contactInfoPage: ContactInformationPage = calculator.clickApplyForYourLoanButton()
    Assertions.assertTrue(contactInfoPage.verifyURL(), "Client is not forwarded to registration")
  }
}