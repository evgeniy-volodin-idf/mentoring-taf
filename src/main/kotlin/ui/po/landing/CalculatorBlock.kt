package ui.po.landing

import com.codeborne.selenide.Selenide.`$`
import org.openqa.selenium.By
import ui.elements.Button
import ui.elements.CommonElement
import ui.elements.Input
import ui.po.ContactInformationPage

class CalculatorBlock {
  private val calculatorBaseBlock: By = By.xpath("//div[@data-test-id='calculator']")
  private val amountInput: By = By.cssSelector("[data-test-id='calculator_amount']")
  private val daysInput: By = By.cssSelector("[data-test-id='calculator_days']")
  private val applyForYourLoanButton: By = By.cssSelector("[class='mainCalculator__center']")
  private val lastLoadedElement: By = By.cssSelector("[class='mainCalculator__info__value']")

  fun verifyCalculatorElementsLoaded() {
    CommonElement.verifyElementVisible(lastLoadedElement)
    CommonElement.verifyElementVisible(calculatorBaseBlock)
  }

  fun getAmount(): String? = `$`(amountInput).value

  fun getDays(): String? = `$`(daysInput).value

  fun updateLoanAmount(updatedAmount: String) {
    Input.clearFieldAndInput(amountInput, updatedAmount)
  }

  fun updateDays(updatedDays: String) {
    Input.clearFieldAndInput(daysInput, updatedDays)
  }

  fun clickApplyForYourLoanButton(): ContactInformationPage {
    Button.clickButton(applyForYourLoanButton)
    return ContactInformationPage()
  }
}

