package ui.assistants

import org.openqa.selenium.Keys
import org.openqa.selenium.WebElement

fun clearFieldAndInput(wedElement: WebElement, inputValue: String){
  wedElement.sendKeys(Keys.chord(Keys.CONTROL, "a"))
  wedElement.sendKeys(inputValue)
}