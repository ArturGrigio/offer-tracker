package org.otracker.db

import org.otracker.db.Tables._
import org.otracker.db.Tables.profile.api._

trait Reads extends Connection {

  // same as "SELECT count(*) FROM Agents"
  val numAgentsQuery: DBIO[Int] = Agents.length.result


  // we can create raw sql queries like this
  val rawSqlQuery: DBIO[Int] = sql"SELECT count(*) FROM agents".as[Int].head

  def getNumAgents(): Int = this.synchronously(numAgentsQuery)


  def getNumOffers(): Int = this.synchronously(Offers.length.result)





  def getOffersForAgent(agentId: Int): Iterable[OffersRow] = {
    val action = for {
      agentOffer <- AgentOffer filter { _.agentId === agentId }
      offer <- Offers filter { _.id === agentOffer.offerId }
    } yield offer


    this.synchronously(action.result)
  }
}
