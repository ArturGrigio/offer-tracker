package org.otracker.lambda

import com.amazonaws.services.lambda.runtime.{Context, RequestStreamHandler}
import org.json4s.{DefaultFormats, JValue}
import org.json4s.JsonDSL._
import org.json4s.jackson.JsonMethods._
import java.io.{DataOutputStream, InputStream, OutputStream}

import org.otracker.db.Db
import org.pmw.tinylog.Logger

/**
  * A simple handler that just returns the total number of rows in the agents table.
  */
object AgentsHandler extends RequestStreamHandler {

  override def handleRequest(input: InputStream, output: OutputStream, context: Context): Unit = {
    implicit val formats = DefaultFormats

    // just an example of parsing json request body
    val json: JValue = parse(input)
    input.close()
    Logger.info(s"invoked with $json")


    // query the db
    val numAgents: Int = Db.getNumAgents()

    // create json response
    val response: String = compact(render("numAgents" -> numAgents))

    val dataOutput: DataOutputStream = new DataOutputStream(output)
    dataOutput.writeChars(response)
    dataOutput.close()
    output.close()
  }
}
