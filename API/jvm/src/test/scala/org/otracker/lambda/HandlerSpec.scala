package org.otracker.lambda

import org.junit.Test
import org.otracker.BaseSpec
import org.pmw.tinylog.Logger

class HandlerSpec extends BaseSpec {


  @Test
  def testOffers(): Unit = {
    val response: String = OffersForAgentHandler.computeResponse(null, null)

    Logger.info(response)
  }


}
