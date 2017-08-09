package org.otracker.db

import org.otracker.db.Tables._
import org.otracker.db.Tables.profile.api._

/**
  * Put all the write queries here.
  */
trait Writes extends Connection {



  /**
    * Inserts the given agent row.
    *
    * @param agent
    * @return the id of the newly inserted row as determined by the database.
    */
  def insertAgent(agent: AgentsRow): Int = {
    val action: DBIO[AgentsRow] = Agents returning Agents.map(_.id) into ((row, newId) => row.copy(id = newId)) += agent
    this.synchronously(action).id
  }


  def insertOffer(offer: OffersRow): Unit = this.synchronously(Offers += offer)
}
