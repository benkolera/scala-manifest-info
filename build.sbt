import ReleaseKeys._
import sbtrelease.{Version,versionFormatError}

organization := "com.benkolera"

name := "manifest-info"

scalaVersion := "2.10.2"

scalacOptions ++= Seq("-feature","-deprecation")

libraryDependencies ++= Seq()

resolvers ++= Seq()

releaseSettings

nextVersion := { ver =>
  Version(ver).map(_.bumpBugfix.asSnapshot.string).getOrElse(versionFormatError)
}

//Make this publish to oss.sonatype.com later
publishTo <<= version { (v: String) =>
  val nexus = "http://jenkins.build.iseek.com.au:8081/nexus/"
  if (v.trim.endsWith("SNAPSHOT"))
    Some("snapshots" at nexus + "content/repositories/snapshots/")
  else
    Some("releases"  at nexus + "content/repositories/releases/")
}

credentials += Credentials(Path.userHome / ".ivy2" / ".credentials")
