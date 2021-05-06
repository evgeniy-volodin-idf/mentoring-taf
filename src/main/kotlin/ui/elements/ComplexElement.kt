package ui.elements

import com.codeborne.selenide.Condition
import com.codeborne.selenide.Selenide.`$`
import org.openqa.selenium.By

object ComplexElement {
  fun verifyObjectIstLoaded(objectLocator: By, lastLoadedElementOfObjectLocator: By): Boolean {
    `$`(lastLoadedElementOfObjectLocator).shouldBe(Condition.visible)
    return `$`(objectLocator).exists()
  }
}