package webshop

trait TimeMeter {
  def timed[B](block: => B) : (B,Long) = {
    val start = System.currentTimeMillis()
    val executionResult = block
    val end = System.currentTimeMillis()
    (executionResult,end-start)
  }
}
