import ReleaseKeys._
import sbtrelease.{Version,versionFormatError}

organization := "com.benkolera"

name := "manifest-info"

scalaVersion := "2.10.3"

scalacOptions ++= Seq("-feature","-deprecation","-Xfatal-warnings")

libraryDependencies ++= Seq()

resolvers ++= Seq()

releaseSettings

nextVersion := { ver =>
  Version(ver).map(_.bumpBugfix.asSnapshot.string).getOrElse(versionFormatError)
}

//Make this publish to oss.sonatype.com later
publishTo <<= version { (v: String) =>
  val nexus = "http://nexus.benkolera.com/nexus/"
  if (v.trim.endsWith("SNAPSHOT"))
    Some("snapshots" at nexus + "content/repositories/snapshots/")
  else
    Some("releases"  at nexus + "content/repositories/releases/")
}
