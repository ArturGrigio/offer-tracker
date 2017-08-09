package org.otracker.db

import slick.jdbc.MySQLProfile.backend.Database
import slick.jdbc.MySQLProfile.api._

import scala.concurrent.Await
import scala.concurrent.duration.Duration
import scala.util.Try

/**
  * Use slick functional-relational mapping.
  *
  * http://slick.lightbend.com/doc/3.2.0/database.html
  *
  * http://underscore.io/training/courses/essential-slick/
  *
  * https://github.com/tomnis/notes/tree/master/slick
  */
trait Connection {

  /**
    * Runs a [[DBIO]] action synchronously within a transactional boundary.
    * This should be simple to convert to an async call if we need those semantics later.
    *
    * The caller is expected to unbox the [[Try]] and implement their own error handling.
    *
    * @param action
    * @tparam Row
    * @return a [[scala.util.Success]] wrapping a query result, or a [[scala.util.Failure]] wrapping an exception.
    */
  def trySynchronously[Row](action: DBIO[Row]): Try[Row] = {
    Try(Await.result(Connection.connection.run(action.transactionally), Duration.Inf))
  }


  /**
    * Runs a [[DBIO]] action synchronously within a transactional boundary.
    *
    * Just delegates to [[trySynchronously()]] and unboxes the result. Be aware that this could throw an exception.
    * @param action
    * @tparam Row
    * @return
    */
  @throws[RuntimeException]("when there is a database error")
  def synchronously[Row](action: DBIO[Row]): Row = this.trySynchronously(action.transactionally).get
}



object Connection {

  // this will use hikari cp for pooled connections
  val connection: Database = Database.forConfig("otracker", Environment.config)

  // shut down our connection on jvm exit
  Runtime.getRuntime.addShutdownHook(
    new Thread {
      override def run(): Unit = Connection.connection.close()
    }
  )
}