package ui.po.landing

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.interactions.Action
import org.openqa.selenium.interactions.Actions
import ui.po.ContactInformationPage
import ui.waiters.Waiters

class CalculatorBlock(private val driver: WebDriver) {
  private val calculator: By = By.xpath("//div[@data-test-id=\"calculator\"]")
  private val sliderAmount: By = By.xpath("//div[contains(@class, 'mainCalculator__sum')]/span")
  private val amount: By = By.cssSelector("[data-test-id=\"calculator_amount\"]")
  private val days: By = By.cssSelector("[data-test-id=\"calculator_days\"]")
  private val sliderDays: By = By.cssSelector(".mainCalculator__date > span")
  private val applyForYourLoanButton: By = By.cssSelector("[class=\"mainCalculator__center\"]")
  private val lastLoadedElement: By = By.cssSelector("[class=\"mainCalculator__info__value\"]")

  fun openURLWithBasicAuthentication(user: String, pass: String, url: String) {
    driver.get("https://$user:$pass@$url")
  }

  fun verifyCalculator(): Boolean {
    return driver.findElements(calculator).isNotEmpty()
  }

  fun verifyDefaultAmount(): String {
    Waiters.waitForElementVisible(driver, lastLoadedElement)
    return driver.findElement(amount).getAttribute("value").toString()
  }

  fun verifyDefaultDays(): String {
    Waiters.waitForElementVisible(driver, lastLoadedElement)
    return driver.findElement(days).getAttribute("value")
  }

  fun updateLoanAmount() {
    val move = Actions(driver)
    val action: Action = move.dragAndDropBy(driver.findElement(sliderAmount), 80, 0).build()
    action.perform()
  }

  fun updateDays() {
    val move = Actions(driver)
    val action: Action = move.dragAndDropBy(driver.findElement(sliderDays), 80, 0).build()
    action.perform()
  }

  fun clickApplyForYourLoanButton(): ContactInformationPage {
    driver.findElement(applyForYourLoanButton).click()
    ContactInformationPage()
    return ContactInformationPage()
  }
}

