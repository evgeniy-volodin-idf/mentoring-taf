package shop.context

object ContextHolder {
  private var context: Context? = null

  fun getContext(): Context {
    if (context == null) {
      context = AppContext()
    }
    return context!!
  }
}