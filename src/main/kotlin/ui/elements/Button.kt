package ui.elements

import com.codeborne.selenide.Condition
import com.codeborne.selenide.Selenide.`$`
import org.openqa.selenium.By

object Button {
  fun clickButton(locator: By) {
    `$`(locator).shouldBe(Condition.visible).click()
  }
}