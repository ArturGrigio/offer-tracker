package org.otracker.lambda

import com.amazonaws.services.lambda.runtime.Context
import org.json4s.JsonDSL._
import org.json4s.jackson.JsonMethods._
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
  def computeResponse(request: JValue, context: Context): String = {

    // TODO we need to get the id out of the path parameter
    val offers: Iterable[OffersRow] = Db.getOffersForAgent(1)


    val numOffers: Int = Db.getNumOffers()

    // TODO actually get the right response
    val response: String = compact(render("numOffers" -> numOffers))

    Logger.info(s"response: $response")

    response
  }
}
