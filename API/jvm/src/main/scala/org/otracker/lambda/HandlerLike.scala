package org.otracker.lambda

import java.io.{DataOutputStream, InputStream, OutputStream}

import com.amazonaws.services.lambda.runtime.{Context, RequestStreamHandler}
import org.json4s.{DefaultFormats, JValue}
import org.json4s.jackson.JsonMethods._
import org.pmw.tinylog.Logger

trait HandlerLike extends RequestStreamHandler {

  /**
    * Parses json request from input stream and writes json response to output stream.
    *
    * @param input
    * @param output
    * @param context
    */
  override def handleRequest(input: InputStream, output: OutputStream, context: Context): Unit = {
    implicit val formats = DefaultFormats

    // just an example of parsing json request body
    val request: JValue = parse(input)
    input.close()
    Logger.info(s"invoked with $request")

    val response: String = this.computeResponse(request, context)

    val dataOutput: DataOutputStream = new DataOutputStream(output)
    dataOutput.writeBytes(response)
    dataOutput.flush()
    dataOutput.close()
    output.flush()
    output.close()
  }


  /**
    * Subclasses should provide an implementation of this method that accepts request json and returns response json.
    *
    * @param request
    * @return response json
    */
  def computeResponse(request: JValue, context: Context): String
}
