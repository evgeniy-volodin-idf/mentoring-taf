package shop.context

import shop.model.Drug

interface Context {
  val event: EventManager

  var soldDrugs: MutableList<Drug>

  val drugsInWarehouse: List<Drug>

  var profit: Long

  fun setSoldDrugsInContext(soldDrugs: MutableList<Drug>)
}