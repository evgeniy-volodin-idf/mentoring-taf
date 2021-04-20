package shop.context

import shop.model.Drug

interface Context {
  var soldDrugs: MutableList<Drug>

  val drugsInWarehouse: List<Drug>
}