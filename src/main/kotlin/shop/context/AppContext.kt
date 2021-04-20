package shop.context

import shop.model.Drug
import shop.provider.WarehouseProvider

class AppContext : Context{
  override var soldDrugs: MutableList<Drug> = mutableListOf()

  override val drugsInWarehouse: List<Drug> = WarehouseProvider().loadDrugsFromFile()
}