package shop.context.observer

import shop.model.Drug

interface EventListener {
  fun update(soldDrugs: List<Drug>)
}