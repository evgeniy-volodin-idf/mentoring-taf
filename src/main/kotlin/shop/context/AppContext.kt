package shop.context

import shop.context.observer.EventManager
import shop.context.observer.EventType
import shop.context.observer.ProfitListener
import shop.model.Drug
import shop.provider.WarehouseProvider

class AppContext : Context {
  private val event: EventManager = EventManager()

  init {
    val profitListener = ProfitListener()
    event.subscribe(EventType.PROFIT, profitListener)
  }

  override var profit: Long = 0

  override var soldDrugs: MutableList<Drug> = mutableListOf()
    set(value) {
      field = value
      event.notifyUpdate(EventType.PROFIT, value)
    }

  override val drugsInWarehouse: List<Drug> = WarehouseProvider().loadDrugsFromFile()
}