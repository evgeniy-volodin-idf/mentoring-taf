package ui.elements

import com.codeborne.selenide.Condition
import com.codeborne.selenide.Selenide.`$`
import org.openqa.selenium.By
import java.time.Duration

object CommonElement {
  fun verifyElementVisible(locator: By) {
    `$`(locator).shouldBe(Condition.visible, Duration.ofSeconds(10))
  }
}