package org.otracker.lambda

import com.amazonaws.services.lambda.runtime.Context
import org.json4s.JValue
import org.json4s.jackson.Serialization.write
import org.otracker.db.Db
import org.otracker.db.Tables.EventsRow

/**
  * Handler looks up all events
  */
object EventsHandler extends HandlerLike {

  /**
    * @param request
    * @return response json string
    */
  override def computeResponse(request: JValue, context: Context): String = {
    val events: Iterable[EventsRow] = Db.getAllEvents()
    write("events" -> events)
  }
}


object EventByIdHandler extends HandlerLike {

  override def computeResponse(request: JValue, context: Context): String = {
    val eventId: Int = (request \ "params" \ "path" \ "event_id").extract[String].toInt
    val events: Iterable[EventsRow] = Db.getEventById(eventId)
    write("event" -> events)
  }
}


object EventsByOfferIdHandler extends HandlerLike {

  override def computeResponse(request: JValue, context: Context): String = {

    val offerId: Int = (request \ "params" \ "path" \ "offer_id").extract[String].toInt
    val events: Iterable[EventsRow] = Db.getEventById(offerId)
    write("event" -> events)
  }
}