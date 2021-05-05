package ui.po.landing

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import ui.assistants.clearFieldAndInput
import ui.po.ContactInformationPage
import ui.waiters.Waiters

class CalculatorBlock(private val driver: WebDriver) {
  private val calculatorBaseBlock: By = By.xpath("//div[@data-test-id='calculator']")
  private val amount: By = By.cssSelector("[data-test-id='calculator_amount']")
  private val days: By = By.cssSelector("[data-test-id='calculator_days']")
  private val applyForYourLoanButton: By = By.cssSelector("[class='mainCalculator__center']")
  private val lastLoadedElement: By = By.cssSelector("[class='mainCalculator__info__value']")

  fun verifyCalculatorElementsLoaded(): Boolean {
    Waiters.waitForElementVisible(driver, lastLoadedElement)
    return driver.findElements(calculatorBaseBlock).isNotEmpty()
  }

  fun getAmount(): String {
    return driver.findElement(amount).getAttribute("value")
  }

  fun getDays(): String {
    return driver.findElement(days).getAttribute("value")
  }

  fun updateLoanAmount(updatedAmount: String) {
    clearFieldAndInput(driver.findElement(amount), updatedAmount)
  }

  fun updateDays(updatedDays: String) {
    clearFieldAndInput(driver.findElement(days), updatedDays)
  }

  fun clickApplyForYourLoanButton(): ContactInformationPage {
    driver.findElement(applyForYourLoanButton).click()
    return ContactInformationPage()
  }
}

