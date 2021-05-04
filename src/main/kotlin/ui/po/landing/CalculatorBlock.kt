package ui.po.landing

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import ui.assistants.clearInput
import ui.po.ContactInformationPage
import ui.waiters.Waiters

class CalculatorBlock(private val driver: WebDriver) {
  private val calculatorBaseBlock: By = By.xpath("//div[@data-test-id='calculator']")
  private val amount: By = By.cssSelector("[data-test-id='calculator_amount']")
  private val days: By = By.cssSelector("[data-test-id='calculator_days']")
  private val applyForYourLoanButton: By = By.cssSelector("[class='mainCalculator__center']")
  private val lastLoadedElement: By = By.cssSelector("[class='mainCalculator__info__value']")
  private val amountElement: WebElement = driver.findElement(amount)
  private val daysElement: WebElement = driver.findElement(days)

  fun verifyCalculatorElementsLoaded(): Boolean {
    Waiters.waitForElementVisible(driver, lastLoadedElement)
    return driver.findElements(calculatorBaseBlock).isNotEmpty()
  }

  fun getSetAmount(): String {
    return amountElement.getAttribute("value")
  }

  fun getSettDays(): String {
    return daysElement.getAttribute("value")
  }

  fun updateLoanAmount(updatedAmount: String) {
    clearInput(amountElement, updatedAmount)
  }

  fun updateDays(updatedDays: String) {
    clearInput(daysElement, updatedDays)
  }

  fun clickApplyForYourLoanButton(): ContactInformationPage {
    driver.findElement(applyForYourLoanButton).click()
    ContactInformationPage()
    return ContactInformationPage()
  }
}

