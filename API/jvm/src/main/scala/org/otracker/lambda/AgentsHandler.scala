package org.otracker.lambda

import com.amazonaws.services.lambda.runtime.Context
import org.json4s.JValue
import org.json4s.JsonDSL._
import org.otracker.db.Db

/**
  * A simple handler that just returns the total number of rows in the agents table.
  */
object AgentsHandler extends HandlerLike {


  override def computeResponse(request: JValue, context: Context): JValue = {

    // query the db
    val numAgents: Int = Db.getNumAgents()

    // create json response
    ("numAgents" -> numAgents)
  }
}
