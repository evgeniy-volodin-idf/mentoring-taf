package ui.elements

import com.codeborne.selenide.Condition
import com.codeborne.selenide.Selenide.`$`
import org.openqa.selenium.By
import java.time.Duration

object CommonElement {
  fun isElementVisible(locator: By): Boolean {
    return `$`(locator).shouldBe(Condition.visible, Duration.ofSeconds(10)).exists()
  }
}