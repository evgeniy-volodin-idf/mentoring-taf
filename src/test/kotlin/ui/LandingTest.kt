package ui

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import ui.po.ContactInformationPage
import ui.po.landing.LandingPage

class LandingTest : BaseTest() {
  private val expectedDefaultAmount = "1,500"
  private val expectedUpdatedAmount = "4,000"
  private val expectedDefaultDays = "10"
  private val expectedUpdatedDays = "12"

  @Test
  fun landingPageVerify() {
    val landingPage = LandingPage()
    landingPage.openURLWithBasicAuthentication()
    val calculator = landingPage.calculatorBlock
    Assertions.assertTrue(calculator.verifyCalculatorElementsLoaded(), "Calculator is not displayed")
    Assertions.assertEquals(
      expectedDefaultAmount,
      calculator.getSetAmount(),
      "Incorrect loan amount is selected by default"
    )
    Assertions.assertEquals(
      expectedDefaultDays,
      calculator.getSettDays(),
      "Incorrect days are selected by default"
    )
    calculator.updateLoanAmount(expectedUpdatedAmount)
    Assertions.assertEquals(expectedUpdatedAmount, calculator.getSetAmount(), "Amount is not updated")
    calculator.updateDays(expectedUpdatedDays)
    Assertions.assertEquals(expectedUpdatedDays, calculator.getSettDays(), "Days are not updated")
    val contactInfoPage: ContactInformationPage = calculator.clickApplyForYourLoanButton()
    Assertions.assertTrue(contactInfoPage.verifyURL(), "Client is not forwarded to registration")
  }
}