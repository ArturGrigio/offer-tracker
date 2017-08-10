package org.otracker.db
// AUTO-GENERATED Slick data model
/** Stand-alone Slick data model for immediate use */
object Tables extends {
  val profile = slick.jdbc.MySQLProfile
} with Tables

/** Slick data model trait for extension, choice of backend or usage in the cake pattern. (Make sure to initialize this late.) */
trait Tables {
  val profile: slick.jdbc.JdbcProfile
  import profile.api._
  import slick.model.ForeignKeyAction
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{GetResult => GR}

  /** DDL for all tables. Call .create to execute. */
  lazy val schema: profile.SchemaDescription = Array(AgentOffer.schema, Agents.schema, Events.schema, Offers.schema, OfferViewer.schema, Sessions.schema, Viewers.schema).reduceLeft(_ ++ _)
  @deprecated("Use .schema instead of .ddl", "3.0")
  def ddl = schema

  /** Entity class storing rows of table AgentOffer
   *  @param agentId Database column agent_id SqlType(INT UNSIGNED)
   *  @param offerId Database column offer_id SqlType(INT UNSIGNED) */
  final case class AgentOfferRow(agentId: Int, offerId: Int)
  /** GetResult implicit for fetching AgentOfferRow objects using plain SQL queries */
  implicit def GetResultAgentOfferRow(implicit e0: GR[Int]): GR[AgentOfferRow] = GR{
    prs => import prs._
    AgentOfferRow.tupled((<<[Int], <<[Int]))
  }
  /** Table description of table agent_offer. Objects of this class serve as prototypes for rows in queries. */
  class AgentOffer(_tableTag: Tag) extends profile.api.Table[AgentOfferRow](_tableTag, Some("tracker"), "agent_offer") {
    def * = (agentId, offerId) <> (AgentOfferRow.tupled, AgentOfferRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(agentId), Rep.Some(offerId)).shaped.<>({r=>import r._; _1.map(_=> AgentOfferRow.tupled((_1.get, _2.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column agent_id SqlType(INT UNSIGNED) */
    val agentId: Rep[Int] = column[Int]("agent_id")
    /** Database column offer_id SqlType(INT UNSIGNED) */
    val offerId: Rep[Int] = column[Int]("offer_id")

    /** Primary key of AgentOffer (database name agent_offer_PK) */
    val pk = primaryKey("agent_offer_PK", (agentId, offerId))

    /** Foreign key referencing Agents (database name agent_offer_agent_id_foreign) */
    lazy val agentsFk = foreignKey("agent_offer_agent_id_foreign", agentId, Agents)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.Cascade)
    /** Foreign key referencing Offers (database name agent_offer_offer_id_foreign) */
    lazy val offersFk = foreignKey("agent_offer_offer_id_foreign", offerId, Offers)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.Cascade)
  }
  /** Collection-like TableQuery object for table AgentOffer */
  lazy val AgentOffer = new TableQuery(tag => new AgentOffer(tag))

  /** Entity class storing rows of table Agents
   *  @param id Database column id SqlType(INT UNSIGNED), AutoInc, PrimaryKey
   *  @param email Database column email SqlType(VARCHAR), Length(255,true), Default(None)
   *  @param password Database column password SqlType(VARCHAR), Length(255,true), Default(None)
   *  @param firstName Database column first_name SqlType(VARCHAR), Length(55,true), Default(None)
   *  @param lastName Database column last_name SqlType(VARCHAR), Length(55,true), Default(None)
   *  @param photoUrl Database column photo_url SqlType(VARCHAR), Length(255,true), Default(None)
   *  @param phoneNumber Database column phone_number SqlType(VARCHAR), Length(20,true), Default(None)
   *  @param brokerage Database column brokerage SqlType(VARCHAR), Length(255,true), Default(None)
   *  @param designations Database column designations SqlType(VARCHAR), Length(255,true), Default(None)
   *  @param createdAt Database column created_at SqlType(TIMESTAMP), Default(None)
   *  @param updatedAt Database column updated_at SqlType(TIMESTAMP), Default(None) */
  final case class AgentsRow(id: Int, email: Option[String] = None, password: Option[String] = None, firstName: Option[String] = None, lastName: Option[String] = None, photoUrl: Option[String] = None, phoneNumber: Option[String] = None, brokerage: Option[String] = None, designations: Option[String] = None, createdAt: Option[java.sql.Timestamp] = None, updatedAt: Option[java.sql.Timestamp] = None)
  /** GetResult implicit for fetching AgentsRow objects using plain SQL queries */
  implicit def GetResultAgentsRow(implicit e0: GR[Int], e1: GR[Option[String]], e2: GR[Option[java.sql.Timestamp]]): GR[AgentsRow] = GR{
    prs => import prs._
    AgentsRow.tupled((<<[Int], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[String], <<?[java.sql.Timestamp], <<?[java.sql.Timestamp]))
  }
  /** Table description of table agents. Objects of this class serve as prototypes for rows in queries. */
  class Agents(_tableTag: Tag) extends profile.api.Table[AgentsRow](_tableTag, Some("tracker"), "agents") {
    def * = (id, email, password, firstName, lastName, photoUrl, phoneNumber, brokerage, designations, createdAt, updatedAt) <> (AgentsRow.tupled, AgentsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), email, password, firstName, lastName, photoUrl, phoneNumber, brokerage, designations, createdAt, updatedAt).shaped.<>({r=>import r._; _1.map(_=> AgentsRow.tupled((_1.get, _2, _3, _4, _5, _6, _7, _8, _9, _10, _11)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(INT UNSIGNED), AutoInc, PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column email SqlType(VARCHAR), Length(255,true), Default(None) */
    val email: Rep[Option[String]] = column[Option[String]]("email", O.Length(255,varying=true), O.Default(None))
    /** Database column password SqlType(VARCHAR), Length(255,true), Default(None) */
    val password: Rep[Option[String]] = column[Option[String]]("password", O.Length(255,varying=true), O.Default(None))
    /** Database column first_name SqlType(VARCHAR), Length(55,true), Default(None) */
    val firstName: Rep[Option[String]] = column[Option[String]]("first_name", O.Length(55,varying=true), O.Default(None))
    /** Database column last_name SqlType(VARCHAR), Length(55,true), Default(None) */
    val lastName: Rep[Option[String]] = column[Option[String]]("last_name", O.Length(55,varying=true), O.Default(None))
    /** Database column photo_url SqlType(VARCHAR), Length(255,true), Default(None) */
    val photoUrl: Rep[Option[String]] = column[Option[String]]("photo_url", O.Length(255,varying=true), O.Default(None))
    /** Database column phone_number SqlType(VARCHAR), Length(20,true), Default(None) */
    val phoneNumber: Rep[Option[String]] = column[Option[String]]("phone_number", O.Length(20,varying=true), O.Default(None))
    /** Database column brokerage SqlType(VARCHAR), Length(255,true), Default(None) */
    val brokerage: Rep[Option[String]] = column[Option[String]]("brokerage", O.Length(255,varying=true), O.Default(None))
    /** Database column designations SqlType(VARCHAR), Length(255,true), Default(None) */
    val designations: Rep[Option[String]] = column[Option[String]]("designations", O.Length(255,varying=true), O.Default(None))
    /** Database column created_at SqlType(TIMESTAMP), Default(None) */
    val createdAt: Rep[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("created_at", O.Default(None))
    /** Database column updated_at SqlType(TIMESTAMP), Default(None) */
    val updatedAt: Rep[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("updated_at", O.Default(None))
  }
  /** Collection-like TableQuery object for table Agents */
  lazy val Agents = new TableQuery(tag => new Agents(tag))

  /** Entity class storing rows of table Events
   *  @param id Database column id SqlType(INT UNSIGNED), AutoInc, PrimaryKey
   *  @param offerId Database column offer_id SqlType(INT UNSIGNED)
   *  @param description Database column description SqlType(VARCHAR), Length(500,true), Default(None)
   *  @param status Database column status SqlType(VARCHAR), Length(255,true), Default(None)
   *  @param statusCode Database column status_code SqlType(VARCHAR), Length(255,true), Default(None)
   *  @param createdAt Database column created_at SqlType(TIMESTAMP), Default(None)
   *  @param updatedAt Database column updated_at SqlType(TIMESTAMP), Default(None) */
  final case class EventsRow(id: Int, offerId: Int, description: Option[String] = None, status: Option[String] = None, statusCode: Option[String] = None, createdAt: Option[java.sql.Timestamp] = None, updatedAt: Option[java.sql.Timestamp] = None)
  /** GetResult implicit for fetching EventsRow objects using plain SQL queries */
  implicit def GetResultEventsRow(implicit e0: GR[Int], e1: GR[Option[String]], e2: GR[Option[java.sql.Timestamp]]): GR[EventsRow] = GR{
    prs => import prs._
    EventsRow.tupled((<<[Int], <<[Int], <<?[String], <<?[String], <<?[String], <<?[java.sql.Timestamp], <<?[java.sql.Timestamp]))
  }
  /** Table description of table events. Objects of this class serve as prototypes for rows in queries. */
  class Events(_tableTag: Tag) extends profile.api.Table[EventsRow](_tableTag, Some("tracker"), "events") {
    def * = (id, offerId, description, status, statusCode, createdAt, updatedAt) <> (EventsRow.tupled, EventsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(offerId), description, status, statusCode, createdAt, updatedAt).shaped.<>({r=>import r._; _1.map(_=> EventsRow.tupled((_1.get, _2.get, _3, _4, _5, _6, _7)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(INT UNSIGNED), AutoInc, PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column offer_id SqlType(INT UNSIGNED) */
    val offerId: Rep[Int] = column[Int]("offer_id")
    /** Database column description SqlType(VARCHAR), Length(500,true), Default(None) */
    val description: Rep[Option[String]] = column[Option[String]]("description", O.Length(500,varying=true), O.Default(None))
    /** Database column status SqlType(VARCHAR), Length(255,true), Default(None) */
    val status: Rep[Option[String]] = column[Option[String]]("status", O.Length(255,varying=true), O.Default(None))
    /** Database column status_code SqlType(VARCHAR), Length(255,true), Default(None) */
    val statusCode: Rep[Option[String]] = column[Option[String]]("status_code", O.Length(255,varying=true), O.Default(None))
    /** Database column created_at SqlType(TIMESTAMP), Default(None) */
    val createdAt: Rep[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("created_at", O.Default(None))
    /** Database column updated_at SqlType(TIMESTAMP), Default(None) */
    val updatedAt: Rep[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("updated_at", O.Default(None))

    /** Foreign key referencing Offers (database name events_offer_id_foreign) */
    lazy val offersFk = foreignKey("events_offer_id_foreign", offerId, Offers)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.Cascade)
  }
  /** Collection-like TableQuery object for table Events */
  lazy val Events = new TableQuery(tag => new Events(tag))

  /** Entity class storing rows of table Offers
   *  @param id Database column id SqlType(INT UNSIGNED), AutoInc, PrimaryKey
   *  @param link Database column link SqlType(VARCHAR), Length(125,true)
   *  @param address Database column address SqlType(VARCHAR), Length(255,true), Default(None)
   *  @param mlsNumber Database column mls_number SqlType(VARCHAR), Length(255,true), Default(None)
   *  @param createdAt Database column created_at SqlType(TIMESTAMP), Default(None)
   *  @param updatedAt Database column updated_at SqlType(TIMESTAMP), Default(None) */
  final case class OffersRow(id: Int, link: String, address: Option[String] = None, mlsNumber: Option[String] = None, createdAt: Option[java.sql.Timestamp] = None, updatedAt: Option[java.sql.Timestamp] = None)
  /** GetResult implicit for fetching OffersRow objects using plain SQL queries */
  implicit def GetResultOffersRow(implicit e0: GR[Int], e1: GR[String], e2: GR[Option[String]], e3: GR[Option[java.sql.Timestamp]]): GR[OffersRow] = GR{
    prs => import prs._
    OffersRow.tupled((<<[Int], <<[String], <<?[String], <<?[String], <<?[java.sql.Timestamp], <<?[java.sql.Timestamp]))
  }
  /** Table description of table offers. Objects of this class serve as prototypes for rows in queries. */
  class Offers(_tableTag: Tag) extends profile.api.Table[OffersRow](_tableTag, Some("tracker"), "offers") {
    def * = (id, link, address, mlsNumber, createdAt, updatedAt) <> (OffersRow.tupled, OffersRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(link), address, mlsNumber, createdAt, updatedAt).shaped.<>({r=>import r._; _1.map(_=> OffersRow.tupled((_1.get, _2.get, _3, _4, _5, _6)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(INT UNSIGNED), AutoInc, PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column link SqlType(VARCHAR), Length(125,true) */
    val link: Rep[String] = column[String]("link", O.Length(125,varying=true))
    /** Database column address SqlType(VARCHAR), Length(255,true), Default(None) */
    val address: Rep[Option[String]] = column[Option[String]]("address", O.Length(255,varying=true), O.Default(None))
    /** Database column mls_number SqlType(VARCHAR), Length(255,true), Default(None) */
    val mlsNumber: Rep[Option[String]] = column[Option[String]]("mls_number", O.Length(255,varying=true), O.Default(None))
    /** Database column created_at SqlType(TIMESTAMP), Default(None) */
    val createdAt: Rep[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("created_at", O.Default(None))
    /** Database column updated_at SqlType(TIMESTAMP), Default(None) */
    val updatedAt: Rep[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("updated_at", O.Default(None))
  }
  /** Collection-like TableQuery object for table Offers */
  lazy val Offers = new TableQuery(tag => new Offers(tag))

  /** Entity class storing rows of table OfferViewer
   *  @param offerId Database column offer_id SqlType(INT UNSIGNED)
   *  @param viewerId Database column viewer_id SqlType(INT UNSIGNED) */
  final case class OfferViewerRow(offerId: Int, viewerId: Int)
  /** GetResult implicit for fetching OfferViewerRow objects using plain SQL queries */
  implicit def GetResultOfferViewerRow(implicit e0: GR[Int]): GR[OfferViewerRow] = GR{
    prs => import prs._
    OfferViewerRow.tupled((<<[Int], <<[Int]))
  }
  /** Table description of table offer_viewer. Objects of this class serve as prototypes for rows in queries. */
  class OfferViewer(_tableTag: Tag) extends profile.api.Table[OfferViewerRow](_tableTag, Some("tracker"), "offer_viewer") {
    def * = (offerId, viewerId) <> (OfferViewerRow.tupled, OfferViewerRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(offerId), Rep.Some(viewerId)).shaped.<>({r=>import r._; _1.map(_=> OfferViewerRow.tupled((_1.get, _2.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column offer_id SqlType(INT UNSIGNED) */
    val offerId: Rep[Int] = column[Int]("offer_id")
    /** Database column viewer_id SqlType(INT UNSIGNED) */
    val viewerId: Rep[Int] = column[Int]("viewer_id")

    /** Primary key of OfferViewer (database name offer_viewer_PK) */
    val pk = primaryKey("offer_viewer_PK", (offerId, viewerId))

    /** Foreign key referencing Offers (database name offer_viewer_offer_id_foreign) */
    lazy val offersFk = foreignKey("offer_viewer_offer_id_foreign", offerId, Offers)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.Cascade)
    /** Foreign key referencing Viewers (database name offer_viewer_viewer_id_foreign) */
    lazy val viewersFk = foreignKey("offer_viewer_viewer_id_foreign", viewerId, Viewers)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.Cascade)
  }
  /** Collection-like TableQuery object for table OfferViewer */
  lazy val OfferViewer = new TableQuery(tag => new OfferViewer(tag))

  /** Entity class storing rows of table Sessions
   *  @param id Database column id SqlType(VARCHAR), Length(64,true)
   *  @param agentId Database column agent_id SqlType(INT UNSIGNED)
   *  @param createdAt Database column created_at SqlType(TIMESTAMP), Default(None)
   *  @param updatedAt Database column updated_at SqlType(TIMESTAMP), Default(None) */
  final case class SessionsRow(id: String, agentId: Int, createdAt: Option[java.sql.Timestamp] = None, updatedAt: Option[java.sql.Timestamp] = None)
  /** GetResult implicit for fetching SessionsRow objects using plain SQL queries */
  implicit def GetResultSessionsRow(implicit e0: GR[String], e1: GR[Int], e2: GR[Option[java.sql.Timestamp]]): GR[SessionsRow] = GR{
    prs => import prs._
    SessionsRow.tupled((<<[String], <<[Int], <<?[java.sql.Timestamp], <<?[java.sql.Timestamp]))
  }
  /** Table description of table sessions. Objects of this class serve as prototypes for rows in queries. */
  class Sessions(_tableTag: Tag) extends profile.api.Table[SessionsRow](_tableTag, Some("tracker"), "sessions") {
    def * = (id, agentId, createdAt, updatedAt) <> (SessionsRow.tupled, SessionsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(agentId), createdAt, updatedAt).shaped.<>({r=>import r._; _1.map(_=> SessionsRow.tupled((_1.get, _2.get, _3, _4)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(VARCHAR), Length(64,true) */
    val id: Rep[String] = column[String]("id", O.Length(64,varying=true))
    /** Database column agent_id SqlType(INT UNSIGNED) */
    val agentId: Rep[Int] = column[Int]("agent_id")
    /** Database column created_at SqlType(TIMESTAMP), Default(None) */
    val createdAt: Rep[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("created_at", O.Default(None))
    /** Database column updated_at SqlType(TIMESTAMP), Default(None) */
    val updatedAt: Rep[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("updated_at", O.Default(None))

    /** Foreign key referencing Agents (database name sessions_agent_id_foreign) */
    lazy val agentsFk = foreignKey("sessions_agent_id_foreign", agentId, Agents)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.Cascade)
  }
  /** Collection-like TableQuery object for table Sessions */
  lazy val Sessions = new TableQuery(tag => new Sessions(tag))

  /** Entity class storing rows of table Viewers
   *  @param id Database column id SqlType(INT UNSIGNED), AutoInc, PrimaryKey
   *  @param firstName Database column first_name SqlType(VARCHAR), Length(55,true), Default(None)
   *  @param lastName Database column last_name SqlType(VARCHAR), Length(55,true), Default(None)
   *  @param phoneNumber Database column phone_number SqlType(VARCHAR), Length(20,true), Default(None)
   *  @param createdAt Database column created_at SqlType(TIMESTAMP), Default(None)
   *  @param updatedAt Database column updated_at SqlType(TIMESTAMP), Default(None) */
  final case class ViewersRow(id: Int, firstName: Option[String] = None, lastName: Option[String] = None, phoneNumber: Option[String] = None, createdAt: Option[java.sql.Timestamp] = None, updatedAt: Option[java.sql.Timestamp] = None)
  /** GetResult implicit for fetching ViewersRow objects using plain SQL queries */
  implicit def GetResultViewersRow(implicit e0: GR[Int], e1: GR[Option[String]], e2: GR[Option[java.sql.Timestamp]]): GR[ViewersRow] = GR{
    prs => import prs._
    ViewersRow.tupled((<<[Int], <<?[String], <<?[String], <<?[String], <<?[java.sql.Timestamp], <<?[java.sql.Timestamp]))
  }
  /** Table description of table viewers. Objects of this class serve as prototypes for rows in queries. */
  class Viewers(_tableTag: Tag) extends profile.api.Table[ViewersRow](_tableTag, Some("tracker"), "viewers") {
    def * = (id, firstName, lastName, phoneNumber, createdAt, updatedAt) <> (ViewersRow.tupled, ViewersRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), firstName, lastName, phoneNumber, createdAt, updatedAt).shaped.<>({r=>import r._; _1.map(_=> ViewersRow.tupled((_1.get, _2, _3, _4, _5, _6)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(INT UNSIGNED), AutoInc, PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column first_name SqlType(VARCHAR), Length(55,true), Default(None) */
    val firstName: Rep[Option[String]] = column[Option[String]]("first_name", O.Length(55,varying=true), O.Default(None))
    /** Database column last_name SqlType(VARCHAR), Length(55,true), Default(None) */
    val lastName: Rep[Option[String]] = column[Option[String]]("last_name", O.Length(55,varying=true), O.Default(None))
    /** Database column phone_number SqlType(VARCHAR), Length(20,true), Default(None) */
    val phoneNumber: Rep[Option[String]] = column[Option[String]]("phone_number", O.Length(20,varying=true), O.Default(None))
    /** Database column created_at SqlType(TIMESTAMP), Default(None) */
    val createdAt: Rep[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("created_at", O.Default(None))
    /** Database column updated_at SqlType(TIMESTAMP), Default(None) */
    val updatedAt: Rep[Option[java.sql.Timestamp]] = column[Option[java.sql.Timestamp]]("updated_at", O.Default(None))
  }
  /** Collection-like TableQuery object for table Viewers */
  lazy val Viewers = new TableQuery(tag => new Viewers(tag))
}
