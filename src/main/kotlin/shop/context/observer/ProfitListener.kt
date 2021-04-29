package shop.context.observer

import shop.context.ContextHolder
import shop.model.Drug

class ProfitListener : EventListener {
  @Suppress("UNCHECKED_CAST")
  override fun update(value: Any) {
    var temp: Long = 0
    (value as List<Drug>).forEach { temp += it.quantity * it.price }
    ContextHolder.getContext().profit += temp
  }
}