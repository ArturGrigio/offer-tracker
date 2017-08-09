package org.otracker.db

import org.otracker.db.Tables._
import org.otracker.db.Tables.profile.api._

trait Reads extends Connection {

  // same as "SELECT count(*) FROM Agents"
  def getNumAgents(): Int = this.synchronously(Agents.length.result)

}
