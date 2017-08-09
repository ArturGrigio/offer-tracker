package org.otracker.db

/**
  * Singleton class for database access.
  *
  * We mix in traits containing read and write functions.
  */
object Db extends Connection with Reads with Writes

