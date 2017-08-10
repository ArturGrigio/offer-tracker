package org.otracker.db

import org.otracker.db.Tables._
import org.otracker.db.Tables.profile.api._

trait Reads extends Connection {

  // same as "SELECT count(*) FROM Agents"
  val numAgentsQuery: DBIO[Int] = Agents.length.result


  // we can create raw sql queries like this
  val rawSqlQuery: DBIO[Int] = sql"SELECT count(*) FROM agents".as[Int].head

  /**
    *
    * @return the number of currently registered agents
    */
  def getNumAgents(): Int = this.synchronously(numAgentsQuery)


  /**
    *
    * @return the number of currently tracked offers
    */
  def getNumOffers(): Int = this.synchronously(Offers.length.result)


  /**
    *
    * @param agentId
    * @return all the [[OffersRow]] associated with this agent id.
    */
  def getOffersForAgent(agentId: Int): Iterable[OffersRow] = {
    val action = for {
      agentOffer <- AgentOffer filter { _.agentId === agentId }
      offer <- Offers filter { _.id === agentOffer.offerId }
    } yield offer


    this.synchronously(action.result)
  }


  /**
    *
    * @param viewerId
    * @return all the [[OffersRow]] associated with this viewer id.
    */
  def getOffersForViewer(viewerId: Int): Iterable[OffersRow] = {
    val action = for {
      offerViewer <- OfferViewer filter { _.viewerId === viewerId }
      offer <- Offers filter { _.id === offerViewer.offerId }
    } yield offer

    this.synchronously(action.result)
  }


  def getAllEvents(): Iterable[EventsRow] = {
    this.synchronously(Events.result)
  }

  def getEventById(eventId: Int): Option[EventsRow] = {
    val action = Events filter { _.id === eventId }
    this.synchronously(action.result.headOption)
  }

  def getEventsForOffer(offerId: Int): Iterable[EventsRow] = {
    val action = Events filter { _.offerId === offerId }
    this.synchronously(action.result)
  }
}
