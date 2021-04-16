package shop

import shop.model.Drug
import java.time.LocalDateTime

class DefaultCart : Cart {
  private var listOfOrderedPosition: MutableList<Drug> = mutableListOf()
  private var soldDrugs: MutableList<Drug> = mutableListOf()

  override fun getListOfDrugsInCart(): MutableList<Drug> {
    return listOfOrderedPosition
  }

  override fun addPosition(drug: Drug) {
    listOfOrderedPosition.add(drug)
  }

  override fun printCart() {
    listOfOrderedPosition.forEach(::println)
  }

  override fun printReceipt() {
    println("Receipt:")
    soldDrugs.forEach{
      println(it)
    }
  }

  override fun addToSoldDrugs() {
    val soldDate = LocalDateTime.now().toString()
    listOfOrderedPosition.onEach {
      soldDrugs.add(it) }.onEach {
      it.dateSold = soldDate }
  }

  override fun clearCart() {
    listOfOrderedPosition.clear()
  }
}