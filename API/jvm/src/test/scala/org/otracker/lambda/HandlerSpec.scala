package org.otracker.lambda

import org.junit.Test
import org.otracker.BaseSpec
import org.pmw.tinylog.Logger

class HandlerSpec extends BaseSpec {

  @Test
  def testOffers(): Unit = {
    // TODO we should actually insert some data and check that we can read it back
    val response: String = OffersForAgentHandler.findOffers(0)
    Logger.info(response)
  }


  @Test
  def testViewerOffers(): Unit = {
    Logger.info(OffersForViewerHandler.findOffers(0))
  }
}
