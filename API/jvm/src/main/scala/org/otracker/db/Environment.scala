package org.otracker.db

import com.typesafe.config.{Config, ConfigFactory}

/**
  * A utility object for reading configuration values.
  *
  * Uses the typesafe config library for application configuration.
  * https://github.com/typesafehub/config
  */
object Environment {

  // TODO support h2 for unit tests
  private val defaults: String =
    """
      |tracker {
      |  driver = "com.mysql.jdbc.Driver"
      |  url = "jdbc:mysql://localhost:3306/tracker"
      |  user = "root"
      |  password = "1234"
      |}
    """.stripMargin

  // TODO double check if we can override these via jvm system properties
  val config: Config = ConfigFactory.systemEnvironment()
    .withFallback(ConfigFactory.systemProperties())
    .withFallback(ConfigFactory.parseString(defaults))
}
