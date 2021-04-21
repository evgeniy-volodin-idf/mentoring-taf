package shop

import shop.context.Context
import shop.context.ContextHolder.getContext
import shop.model.Drug
import java.time.LocalDateTime

class DefaultCart(private var appContext: Context = getContext()) : Cart {

  private var listOfOrderedPosition: MutableList<Drug> = mutableListOf()

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
    getContext().soldDrugs.forEach(::println)
  }

  override fun addToSoldDrugs() {
    val soldDate = LocalDateTime.now().toString()
    listOfOrderedPosition.forEach {
      it.dateSold = soldDate
    }
    appContext.soldDrugs.addAll(listOfOrderedPosition)
  }

  override fun clearCart() {
    listOfOrderedPosition.clear()
  }
}