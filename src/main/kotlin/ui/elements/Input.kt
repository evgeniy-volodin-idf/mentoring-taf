package ui.elements

import com.codeborne.selenide.Selenide.`$`
import org.openqa.selenium.By
import org.openqa.selenium.Keys

object Input {
  fun getDefaultAttributeValue(locator: By): String? = `$`(locator).value

  fun clearFieldAndInput(locator: By, inputValue: String) {
    `$`(locator).apply {
      sendKeys(Keys.chord(Keys.CONTROL, "a"))
      sendKeys(inputValue)
    }
  }
}