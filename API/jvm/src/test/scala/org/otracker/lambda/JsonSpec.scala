package org.otracker.lambda

import org.json4s.{DefaultFormats, FieldSerializer}
import org.junit.Test
import org.json4s._
import org.json4s.JsonDSL._
import org.json4s.jackson.JsonMethods._
import org.json4s.jackson.Serialization.write
import org.otracker.BaseSpec
import org.otracker.db.Tables.OffersRow
import org.pmw.tinylog.Logger

class JsonSpec extends BaseSpec {

  /**
    * Checks that we can correctly serialize a list of offers
    */
  @Test
  def offerJson(): Unit = {
    implicit val formats = DefaultFormats
    val offer1: OffersRow = OffersRow(
      id = 0,
      link = "https://localhost:12345"
    )
    val offer2: OffersRow = OffersRow(
      id = 1,
      link = "https://some.link.com",
      address = Option("123 fake street")
    )

    val offers: Iterable[OffersRow] = List(offer1, offer2)

    val json: String = write("offers" -> offers)
    Logger.info(s"serialized: $json")
    json should be ("""{"offers":[{"id":0,"link":"https://localhost:12345"},{"id":1,"link":"https://some.link.com","address":"123 fake street"}]}""")
  }
}
