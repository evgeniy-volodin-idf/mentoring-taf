package shop

fun printlnRed(message: Any?) {
  println("${Col.RED.collorCode}  $message  ${Col.REST.collorCode}")
}
fun printlnYellow(message: Any?) {
  println("${Col.YELLOW.collorCode} $message ${Col.REST.collorCode}")
}
