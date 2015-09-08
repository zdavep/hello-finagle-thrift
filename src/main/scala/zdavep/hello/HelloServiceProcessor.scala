package zdavep
package hello

import com.twitter.logging.Logger
import com.twitter.util.Future

/**
 * The HelloService implementation.
 */
object HelloServiceProcessor {

  private val logger = Logger.get(getClass)

  /**
   * HelloService implementation.
   */
  def apply(): HelloService[Future] = new HelloService[Future] {

    /**
     * The sayHello service function implementation
     */
    override def sayHello(msg: HelloMsg): Future[HelloMsg] = Future.value {
      logger.info(s"Server received message: ${msg.name}")
      HelloMsg(s"Hello, ${msg.name}")
    }

    /**
     * The ping service function implementation
     */
    override def ping(): Future[Unit] = {
      logger.info("ping called")
      Future.Unit
    }
  }
}
