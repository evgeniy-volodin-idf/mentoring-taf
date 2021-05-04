package ui.waiters

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait

object Waiters {
  private const val WAIT_ELEMENT_SECONDS = 10

  fun waitForElementVisible(driver: WebDriver, locator: By, waitSecondsTimeout: Int = WAIT_ELEMENT_SECONDS) {
    WebDriverWait(
      driver,
      waitSecondsTimeout.toLong()
    ).until(ExpectedConditions.visibilityOfElementLocated(locator))
  }
}