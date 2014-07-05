import sbt._
import sbt.Keys._

object MyBuild extends Build {

  val akkaTut =
    Project("spray-markdown", file(".")) settings(
      organization := "com.example",
      name := "spray-markdown",
      scalaVersion := "2.10.4",
      resolvers += "spray" at "http://repo.spray.io/",
      libraryDependencies ++= dependencies,
      libraryDependencies += "org.pegdown" % "pegdown" % "1.4.2",
      libraryDependencies += "org.ow2.asm" % "asm-all" % "4.0",
      autoCompilerPlugins := true,
      scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")
    )

  def publishSettings: Seq[Setting[_]] = Seq(
    // If we want on maven central, we need to be in maven style.
    publishMavenStyle := true,
    publishArtifact in Test := false)

  object versions {
    object scala {
      val lang = "2.10.4"
      val test = "2.1.7"
    }
    object spray {
      val lib = "1.3.1"
      val json = "1.2.6"
      val shapeless = "1.2.4"
    }
    object akka {
      val lib = "2.3.3"
      val cfg = "1.2.0"
    }
  }

  def dependencies: Seq[ModuleID] = (scalaDeps ++ akkaDeps ++ sprayDeps) .map { dep => dep withSources() withJavadoc() }

  def scalaDeps: Seq[ModuleID] =
    Seq(
      "org.scala-lang" % "scala-library" % versions.scala.lang,
      "org.scalatest" %% "scalatest" % versions.scala.test % "test"
    )

  def akkaDeps: Seq[ModuleID] =
    Seq(
      "com.typesafe.akka" %% "akka-osgi" % versions.akka.lib ,
      "com.typesafe.akka" %% "akka-cluster" % versions.akka.lib ,
      "com.typesafe.akka" %% "akka-camel" % versions.akka.lib ,
      "com.typesafe.akka" %% "akka-slf4j" % versions.akka.lib ,
      "com.typesafe" % "config" % versions.akka.cfg ,
      "com.typesafe.akka" %% "akka-testkit" % versions.akka.lib % "test"
    )

  def sprayDeps: Seq[ModuleID] =
    Seq(
      "io.spray" % "spray-http" % versions.spray.lib ,
      "io.spray" % "spray-httpx" % versions.spray.lib ,
      "io.spray" % "spray-routing" % versions.spray.lib ,
      "io.spray" % "spray-util" % versions.spray.lib ,
      "io.spray" % "spray-io" % versions.spray.lib ,
      "io.spray" % "spray-can" % versions.spray.lib ,
      "io.spray" % "spray-client" % versions.spray.lib ,
      "com.chuusai" %% "shapeless" % versions.spray.shapeless ,
      "io.spray" %% "spray-json" % versions.spray.json ,
      "io.spray" % "spray-testkit" % versions.spray.lib % "test"
  )
}
