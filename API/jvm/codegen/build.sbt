val slickVersion = "3.2.0"

lazy val mainProject = Project(
  id="otracker-slick-codegen",
  base=file("."),
  settings = Defaults.coreDefaultSettings ++ Seq(
    scalaVersion := "2.12.0",
    libraryDependencies ++= List(
      "com.typesafe.slick" %% "slick" % slickVersion,
      "com.typesafe.slick" %% "slick-codegen" % slickVersion,
      "org.slf4j" % "slf4j-nop" % "1.7.19",
      "mysql" % "mysql-connector-java" % "5.1.41",
      "com.h2database" % "h2" % "1.4.191"
    ),
    slick <<= slickCodeGenTask // register manual sbt command
  )
)

// code generation task
lazy val slick = TaskKey[Seq[File]]("gen-tables")
lazy val slickCodeGenTask = (sourceManaged, dependencyClasspath in Compile, runner in Compile, streams) map { (dir, cp, r, s) =>
  val outputDir = (dir / "slick").getPath // place generated files in sbt's managed sources folder
  val url = "jdbc:mysql://localhost:3306/tracker?user=root&password=1234" 
  val jdbcDriver = "com.mysql.jdbc.Driver"
  val slickDriver = "slick.jdbc.MySQLProfile"
  val pkg = "org.otracker.db"
  toError(r.run("slick.codegen.SourceCodeGenerator", cp.files, Array(slickDriver, jdbcDriver, url, outputDir, pkg), s.log))
  val fname = outputDir + "/demo/Tables.scala"
  Seq(file(fname))
}
