package org.otracker.db

import com.typesafe.config.{Config, ConfigFactory}

/**
  * Use the typesafe config library for application configuration.
  */
object Environment {

  // TODO support h2 for unit tests
  private val conf: String =
    """
      |otracker {
      |  driver = "com.mysql.jdbc.Driver"
      |  url = "jdbc:mysql://localhost:3306/otracker"
      |  user = "root"
      |  password = "1234"
      |}
    """.stripMargin

  // TODO double check if we can override these via jvm system properties
  val config: Config = ConfigFactory.parseString(conf).withFallback(ConfigFactory.systemProperties())
}
