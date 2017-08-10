package org.otracker.lambda

import java.io.{DataOutputStream, InputStream, OutputStream}

import com.amazonaws.services.lambda.runtime.{Context, RequestStreamHandler}
import org.json4s.{DefaultFormats, JValue}
import org.json4s.jackson.JsonMethods._
import org.pmw.tinylog.Logger

/**
  * A thin wrapper around [[RequestStreamHandler]].
  *
  * Handles parsing of request json body and provides a method stub that must be overridden to implement an endpoint.
  */
trait HandlerLike extends RequestStreamHandler {

  implicit val formats = DefaultFormats

  /**
    * Parses json request from input stream and writes json response to output stream.
    *
    * @param input
    * @param output
    * @param context
    */
  override def handleRequest(input: InputStream, output: OutputStream, context: Context): Unit = {
    // just an example of parsing json request body
    val request: JValue = parse(input)
    input.close()
    Logger.info(s"invoked with $request")

    // invoke our concrete implementation
    val response: String = this.computeResponse(request, context)

    val dataOutput: DataOutputStream = new DataOutputStream(output)
    dataOutput.writeBytes(response)
    dataOutput.flush()
    dataOutput.close()
    output.flush()
    output.close()
  }


  /**
    * Subclasses should implement this method stub that accepts request json and returns response json string.
    *
    * See [[OffersForAgentHandler]] for an example that includes serialization using [[org.json4s.Serialization.write]]
    *
    * @param request
    * @return response json string
    */
  // method with no body is abstract
  def computeResponse(request: JValue, context: Context): String
}
