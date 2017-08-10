package org.otracker.db

import com.typesafe.config.{Config, ConfigFactory}

import scala.collection.JavaConverters.mapAsJavaMap

/**
  * A utility object for reading configuration values.
  *
  * Uses the typesafe config library for application configuration.
  * https://github.com/typesafehub/config
  */
object Environment {

  private val driver: String = "com.mysql.jdbc.Driver"

  // TODO support h2 for unit tests
  private val defaults: String =
    s"""
      |tracker {
      |  driver = $driver
      |  url = "jdbc:mysql://localhost:3306/tracker?user=root&password=1234"
      |}
    """.stripMargin

  // for prod, we need to encode the real credentials as env vars
  private val maybeUrl: Option[String] = Option(System.getenv("DB_URL"))

  private val creds: Map[String, String] = maybeUrl match {
    case Some(url) => Map("tracker.url" -> url)
    case None => Map.empty
  }

  val config: Config = ConfigFactory.systemEnvironment()
    .withFallback(ConfigFactory.systemProperties())
    .withFallback(ConfigFactory.parseMap(mapAsJavaMap(creds)))
    .withFallback(ConfigFactory.parseString(defaults))
}
