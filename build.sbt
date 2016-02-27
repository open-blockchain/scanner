import scala.collection.JavaConverters._

name := "SuperChain"

organization := "org.dyne.danielsan"

version := "0.1.0-SNAPSHOT"

scalaVersion := "2.11.7"

crossScalaVersions := Seq("2.10.4", "2.11.2")

resolvers ++= Seq(
  "Typesafe repository snapshots" at "http://repo.typesafe.com/typesafe/snapshots/",
  "Typesafe repository releases" at "http://repo.typesafe.com/typesafe/releases/",
  "Sonatype repo"                    at "https://oss.sonatype.org/content/groups/scala-tools/",
  "Sonatype releases"                at "https://oss.sonatype.org/content/org.dyne.danielsan.superchain.data.cassandra.init.repositories/releases",
  "Sonatype snapshots"               at "https://oss.sonatype.org/content/org.dyne.danielsan.superchain.data.cassandra.init.repositories/snapshots",
  "Sonatype staging"                 at "http://oss.sonatype.org/content/org.dyne.danielsan.superchain.data.cassandra.init.repositories/staging",
  "Java.net Maven2 Repository"       at "http://download.java.net/maven/2/",
  "Twitter Repository"               at "http://maven.twttr.com",
  "Wedsudos Bintray Repo"            at "https://dl.bintray.com/websudos/oss-releases/"
)

libraryDependencies ++= Seq(
  "com.websudos" %% "phantom-dsl" % "1.12.2",
  "org.scalatest" %% "scalatest" % "2.2.1" % "test",
  "org.scalacheck" %% "scalacheck" % "1.11.5" % "test",
  "io.argonaut" %% "argonaut" % "6.1",
  "net.databinder.dispatch" %% "dispatch-core" % "0.11.2",
  "org.json4s" %% "json4s-native" % "3.3.0",
  "org.json4s" %% "json4s-jackson" % "3.3.0"
)

initialCommands := "import org.dyne.danielsan.superchain._"
