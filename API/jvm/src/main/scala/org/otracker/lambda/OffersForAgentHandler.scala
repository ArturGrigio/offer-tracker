package org.otracker.lambda

import com.amazonaws.services.lambda.runtime.Context
import org.json4s.jackson.Serialization.write
import org.json4s.JValue
import org.otracker.db.Db
import org.otracker.db.Tables.OffersRow
import org.pmw.tinylog.Logger

/**
  * A handler that gets all offers for an agent.
  *
  * GET /api/agents/{agent_id}/offers
  */
object OffersForAgentHandler extends HandlerLike {

  /**
    *
    * @param request
    * @return response json
    */
  override def computeResponse(request: JValue, context: Context): String = {
    // see aws api gateway where the url path is mapped into the request body
    val agentId: Int = (request \ "params" \ "path" \ "agent_id").extract[String].toInt
    Logger.info(s"finding offers for agentId: $agentId")
    this.findOffers(agentId)
  }


  /**
    * Looks up all the offers for the given agent id.
    *
    * @param agentId
    * @return
    */
  def findOffers(agentId: Int): String = {
    val offers: Iterable[OffersRow] = Db.getOffersForAgent(agentId)
    val response: String = write(OffersForAgent(agentId, offers))
    Logger.info(s"response: $response")
    response
  }
}


// a simple value class to represent a response ready for serialization
case class OffersForAgent(agent_id: Int, offers: Iterable[OffersRow])
