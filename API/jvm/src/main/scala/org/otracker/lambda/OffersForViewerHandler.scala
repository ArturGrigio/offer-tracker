package org.otracker.lambda

import com.amazonaws.services.lambda.runtime.Context
import org.json4s.JValue
import org.json4s.jackson.Serialization.write
import org.otracker.db.Db
import org.otracker.db.Tables.OffersRow
import org.pmw.tinylog.Logger

/**
  * A Handler that gets all offers for a viewer.
  */
object OffersForViewerHandler extends HandlerLike {


  override def computeResponse(request: JValue, context: Context): String = {
    // see aws api gateway where the url path is mapped into the request body
    val viewerId: Int = (request \ "params" \ "path" \ "viewer_id").extract[String].toInt
    Logger.info(s"finding offers for viewer id: $viewerId")
    this.findOffers(viewerId)
  }


  def findOffers(viewerId: Int): String = {
    val offers: Iterable[OffersRow] = Db.getOffersForViewer(viewerId)
    val response: String = write(OffersForViewer(viewerId, offers))
    Logger.info(s"response: $response")
    response
  }
}


case class OffersForViewer(viewer_id: Int, offers: Iterable[OffersRow])
