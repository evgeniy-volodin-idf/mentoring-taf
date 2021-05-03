package ui.waiters

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait

object Waiters {
  private const val waitForElementsSec = 10

  fun waitForElementVisible(driver: WebDriver, locator: By) {
    WebDriverWait(
      driver,
      waitForElementsSec.toLong()
    ).until(ExpectedConditions.visibilityOfElementLocated(locator))
  }
}