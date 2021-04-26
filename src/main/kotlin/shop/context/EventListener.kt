package shop.context

import shop.model.Drug

interface EventListener {
  fun update(soldDrugs: List<Drug>)
}