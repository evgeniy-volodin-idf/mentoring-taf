package shop.context

import shop.model.Drug

class ProfitListener : EventListener {
  override fun update(soldDrugs: List<Drug>) {
    var temp: Long = 0
    soldDrugs.forEach{ temp += it.quantity * it.price }
    ContextHolder.getContext().profit += temp
  }
}