package ui.driver

import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import java.util.concurrent.TimeUnit

class DriverConfiguration {
  private val propertyChrome = "webdriver.chrome.driver"
  private val pathToChromeDriver = "C:\\WebDriver\\bin\\chromedriver.exe"

  fun getWebDriver(): WebDriver {
    System.setProperty(propertyChrome, pathToChromeDriver)
    val driver: WebDriver = ChromeDriver()
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS)
    return driver
  }
}