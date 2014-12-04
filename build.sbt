seq(conscriptSettings :_*)

organization := "hm.orz.sumpic"

name := "funiki-crawler"

version := "0.1.0"

scalaVersion := "2.11.1"

publishTo := Some(Resolver.file("funiki-crawler",file("./"))(Patterns(true, Resolver.mavenStyleBasePattern)))

libraryDependencies ++= Seq(
  "org.specs2" %% "specs2" % "2.3.11" % "test",
  "org.scalatest" %% "scalatest" % "2.2.1-M3" % "test"
)

resolvers ++= Seq("snapshots", "releases").map(Resolver.sonatypeRepo)

ideaExcludeFolders += ".idea"

ideaExcludeFolders += ".idea_modules"

