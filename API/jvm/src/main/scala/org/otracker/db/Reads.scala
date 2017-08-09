package org.otracker.db

import org.otracker.db.Tables._
import org.otracker.db.Tables.profile.api._

trait Reads extends Connection {

  // same as "SELECT count(*) FROM Agents"
  val numAgentsQuery: DBIO[Int] = Agents.length.result


  val rawSqlQuery: DBIO[Int] = sql"SELECT count(*) FROM Agents".as[Int].head


  this.synchronously(rawSqlQuery)

  def getNumAgents(): Int = this.synchronously(numAgentsQuery)

}
