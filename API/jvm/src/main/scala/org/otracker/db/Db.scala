package org.otracker.db

/**
  * Singleton class for database access.
  *
  * We mix in traits containing read and write functions.
  *
  * Reference this object for all db interaction.
  */
object Db extends Connection with Reads with Writes

