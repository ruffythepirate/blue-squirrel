// import org.scoverage.coveralls.Imports.CoverallsKeys._

name := """blue-squirrel"""

version := "0.1-BETA"

lazy val root = (project in file("."))
  .enablePlugins(PlayScala, SbtWeb)
  .configs(IntegrationTest)

Defaults.itSettings

(sourceDirectory in IntegrationTest) := baseDirectory.value / "it"

scalaVersion := "2.11.8"
sbtVersion := "0.13.11"

libraryDependencies ++= Seq(jdbc,
  cache,
  ws,
  evolutions
)



libraryDependencies ++= Seq(
  "org.scalatestplus.play" %% "scalatestplus-play" % "1.5.1",
  "org.mockito" % "mockito-core" % "2.2.22",
  "com.typesafe.play" %% "anorm" % "2.4.0",
  "com.atlassian.commonmark" % "commonmark" % "0.2.0",
  "org.webjars" %% "webjars-play" % "2.4.0-1",
  "org.webjars" % "bootstrap" % "3.1.1-2",
  "org.webjars" % "flat-ui" % "bcaf2de95e"
)


resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"

// Play provides two styles of routers, one expects its actions to be injected, the
// other, legacy style, accesses its actions statically.
routesGenerator := InjectedRoutesGenerator

// coverageEnabled := true
// coverageEnabled in Test := true
// coverageEnabled in IntegrationTest := true

// coverallsToken := {
//   val token = IO.read(new File("project/coverall/coverallToken.txt"))
//   Some(token)
// }
