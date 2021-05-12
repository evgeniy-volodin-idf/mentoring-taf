package ui

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import ui.po.ContactInformationPage
import ui.po.landing.LandingPage

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class LandingTest : BaseTest() {
  private val expectedDefaultAmount = "1,500"
  private val expectedUpdatedAmount = "4,000"
  private val expectedDefaultDays = "10"
  private val expectedUpdatedDays = "12"

  @Test
  fun landingPageVerify() {
    LandingPage().apply {
      openPage()
      Assertions.assertTrue(calculatorBlock.verifyCalculatorElementsLoaded(), "Calculator is not displayed")
      Assertions.assertEquals(
        expectedDefaultAmount,
        calculatorBlock.getAmount(),
        "Incorrect loan amount is selected by default"
      )
      Assertions.assertEquals(
        expectedDefaultDays,
        calculatorBlock.getDays(),
        "Incorrect days are selected by default"
      )
      calculatorBlock.updateLoanAmount(expectedUpdatedAmount)
      Assertions.assertEquals(expectedUpdatedAmount, calculatorBlock.getAmount(), "Amount is not updated")
      calculatorBlock.updateDays(expectedUpdatedDays)
      Assertions.assertEquals(expectedUpdatedDays, calculatorBlock.getDays(), "Days are not updated")
      val contactInfoPage: ContactInformationPage = calculatorBlock.clickApplyForYourLoanButton()
      Assertions.assertTrue(contactInfoPage.isURL(), "Client is not forwarded to registration")
    }
  }
}