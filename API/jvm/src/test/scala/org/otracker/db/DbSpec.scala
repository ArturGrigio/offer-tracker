package org.otracker.db

import org.junit.{Before, Test}
import org.otracker.BaseSpec
import org.otracker.db.Tables.AgentsRow
import org.otracker.db.Tables.profile.api._
import org.pmw.tinylog.Logger

class DbSpec extends BaseSpec with Connection {

  /** Truncate the schema before each test. */
//  @Before
//  def truncate(): Unit = {
//    this.synchronously(Tables.schema.drop andThen Tables.schema.create)
//  }

  /** Checks that we can insert an agent correctly. */
  @Test
  def insertAgent(): Unit = {

    Db.getNumAgents() should be (0)
    // somewhat confusingly, the id we use at construction time is silently ignored by the db so a
    // real id can be assigned
    val agent: AgentsRow = AgentsRow(id = 0)
    Db.insertAgent(agent) should be (1)

    Db.getNumAgents() should be (1)
  }


  @Test
  def numOffers(): Unit = {
    Logger.info(s"there are ${Db.getNumOffers()} offers")
  }
}
