#! /bin/bash
# use this to regenerate our case classes when the schema has changed

sbt gen-tables
mkdir -pv ../src/main/scala/org/otracker/db
cp target/scala-2.12/src_managed/slick/org/otracker/db/Tables.scala ../src/main/scala/org/otracker/db
