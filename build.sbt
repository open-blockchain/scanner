name := """SuperChain"""

organization := "org.dyne.danielsan"

version := "0.1.0-SNAPSHOT"

crossScalaVersions := Seq("2.10.4", "2.11.2")

scalaVersion := "2.11.7"

lazy val phantomVersion = "1.22.0"
lazy val cassandraVersion = "2.1.4"
lazy val driverCore = "3.0.0-rc1"

resolvers ++= Seq(
  "Typesafe repository snapshots" at "http://repo.typesafe.com/typesafe/snapshots/",
  "Typesafe repository releases" at "http://repo.typesafe.com/typesafe/releases/",
  "Sonatype repo" at "https://oss.sonatype.org/content/groups/scala-tools/",
  "Sonatype releases" at "https://oss.sonatype.org/content/org.dyne.danielsan.superchain.data.cassandra.init.repositories/releases",
  "Sonatype snapshots" at "https://oss.sonatype.org/content/org.dyne.danielsan.superchain.data.cassandra.init.repositories/snapshots",
  "Sonatype staging" at "http://oss.sonatype.org/content/org.dyne.danielsan.superchain.data.cassandra.init.repositories/staging",
  "Java.net Maven2 Repository" at "http://download.java.net/maven/2/",
  "Twitter Repository" at "http://maven.twttr.com",
  "Wedsudos Bintray Repo" at "https://dl.bintray.com/websudos/oss-releases/"
)

libraryDependencies ++= Seq(
  "org.mockito" % "mockito-core" % "1.10.19",
  "org.json4s" %% "json4s-jackson" % "3.3.0",
  "org.scalaj" %% "scalaj-http" % "2.2.1",
  "org.scalatest" %% "scalatest" % "2.2.1" % "test",
  "org.scalacheck" %% "scalacheck" % "1.11.5" % "test",
  "com.websudos" %% "phantom-dsl" % phantomVersion,
  "com.datastax.cassandra" % "cassandra-driver-core" % driverCore,
  "org.apache.cassandra" % "cassandra-all" % cassandraVersion)

initialCommands := "import org.dyne.danielsan.superchain._"