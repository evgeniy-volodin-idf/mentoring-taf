package ui.po

import org.openqa.selenium.WebDriver
import ui.driver.WebDriverSingleton

open class AbstractPage(val driver: WebDriver = WebDriverSingleton.getWebDriverInstance()) {
}