package ui.assistants

import org.openqa.selenium.WebElement

fun clearInput(wedElement: WebElement, inputValue: String){
  wedElement.clear()
  wedElement.sendKeys(inputValue)
}