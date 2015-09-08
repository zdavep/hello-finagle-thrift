package zdavep
package app

import com.twitter.finagle.builder.ServerBuilder
import com.twitter.finagle.thrift.ThriftServerFramedCodec
import com.twitter.logging.Logger
import hello._

/**
 * Start the hello service server on a given port.
 */
object ServerApp extends App {

  val logger = Logger.get(getClass)

  val defPort = 5555
  val name = "/zdavep/hello/v1"
  val port = if (args.length > 0) args(0).toInt else defPort
  val serverHost = new java.net.InetSocketAddress(port)

  logger.info(s"Starting ${name} server...")
  val protocol = new org.apache.thrift.protocol.TBinaryProtocol.Factory()
  val service = new HelloService$FinagleService(HelloServiceProcessor(), protocol)
  ServerBuilder().bindTo(serverHost).codec(ThriftServerFramedCodec()).name(name).build(service)

  logger.info("Joining ZooKeeper cluster...")
  val _ = ZooKeeperHelper.join(name, serverHost)
}
