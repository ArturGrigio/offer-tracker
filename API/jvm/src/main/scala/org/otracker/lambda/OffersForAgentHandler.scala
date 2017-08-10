package org.otracker.lambda
import com.amazonaws.services.lambda.runtime.Context
import org.json4s.JsonDSL._
import org.json4s.JValue
import org.otracker.db.Db
import org.otracker.db.Tables.OffersRow

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
  override def computeResponse(request: JValue, context: Context): JValue = {

    // TODO we need to get the id out of the path parameter
    val offers: Iterable[OffersRow] = Db.getOffersForAgent(1)

    ("offers" -> "")
  }
}
