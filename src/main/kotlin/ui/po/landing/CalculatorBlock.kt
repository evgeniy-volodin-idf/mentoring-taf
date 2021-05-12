package ui.po.landing

import com.codeborne.selenide.Selenide.`$`
import org.openqa.selenium.By
import ui.elements.Button
import ui.elements.CommonElement
import ui.elements.Input
import ui.po.ContactInformationPage

class CalculatorBlock {
  private val calculatorBaseBlock: By = By.xpath("//div[@data-test-id='calculator']")
  private val amount: By = By.cssSelector("[data-test-id='calculator_amount']")
  private val days: By = By.cssSelector("[data-test-id='calculator_days']")
  private val applyForYourLoanButton: By = By.cssSelector("[class='mainCalculator__center']")
  private val lastLoadedElement: By = By.cssSelector("[class='mainCalculator__info__value']")

  fun verifyCalculatorElementsLoaded(): Boolean {
    CommonElement.isElementVisible(lastLoadedElement)
    return CommonElement.isElementVisible(calculatorBaseBlock)
  }

  fun getAmount(): String? = `$`(amount).value

  fun getDays(): String? = `$`(days).value

  fun updateLoanAmount(updatedAmount: String) {
    Input.clearFieldAndInput(amount, updatedAmount)
  }

  fun updateDays(updatedDays: String) {
    Input.clearFieldAndInput(days, updatedDays)
  }

  fun clickApplyForYourLoanButton(): ContactInformationPage {
    Button.clickButton(applyForYourLoanButton)
    return ContactInformationPage()
  }
}

