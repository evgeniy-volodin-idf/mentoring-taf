package shop.context

import shop.model.Drug
import shop.provider.WarehouseProvider

class AppContext : Context {
  override val event: EventManager = EventManager()

  init {
    val profitListener = ProfitListener()
    event.subscribe(EventType.PROFIT, profitListener)
  }

  override var profit: Long = 0

  override var soldDrugs: MutableList<Drug> = mutableListOf()

  override fun setSoldDrugsInContext(soldDrugs: MutableList<Drug>) {
    this.soldDrugs = soldDrugs
    event.notifyUpdate(EventType.PROFIT, soldDrugs)
  }

  override val drugsInWarehouse: List<Drug> = WarehouseProvider().loadDrugsFromFile()
}