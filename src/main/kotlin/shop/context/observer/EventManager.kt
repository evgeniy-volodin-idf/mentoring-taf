package shop.context.observer

import shop.model.Drug

open class EventManager {
  private var listeners: MutableMap<EventType, EventListener> = mutableMapOf()

  fun subscribe(event: EventType, listener: EventListener) {
    listeners = mutableMapOf(event to listener)
  }

  fun unsubscribe(event: EventType) {
    listeners.remove(event)
  }

  fun notifyUpdate(event: EventType, listOfDrugs: List<Drug>) {
    listeners[event]!!.update(listOfDrugs)
  }
}