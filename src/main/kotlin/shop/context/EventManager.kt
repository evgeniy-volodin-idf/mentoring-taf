package shop.context

import shop.model.Drug

open class EventManager {
  private var listeners: Map<EventType, EventListener> = mutableMapOf()

  fun subscribe(event: EventType, listener: EventListener) {
    listeners = mutableMapOf(event to listener)
  }

  fun unsubscribe(event: EventType) {
    listeners = listeners - event
  }

  fun notifyUpdate(event: EventType, listOfDrugs: List<Drug>) {
    listeners[event].apply {
      this!!.update(listOfDrugs)
    }
  }
}