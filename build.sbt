seq(conscriptSettings :_*)

organization := "hm.orz.sumpic"


name := "funiki-crawler"

version := "0.2.0"

scalaVersion := "2.11.1"

publishTo := Some(Resolver.file("funiki-crawler",file("./target/repo"))(Patterns(true, Resolver.mavenStyleBasePattern)))

libraryDependencies ++= Seq(
  "org.specs2" %% "specs2" % "2.3.11" % "test",
  "org.scalatest" %% "scalatest" % "2.2.1-M3" % "test",
  "org.apache.lucene" % "lucene-kuromoji" % "3.6.2",
  "org.jsoup" % "jsoup" % "1.8.1" ,
  "net.debasishg" % "redisclient_2.11" % "2.14"
)

resolvers ++= Seq("snapshots", "releases").map(Resolver.sonatypeRepo)

ideaExcludeFolders += ".idea"

ideaExcludeFolders += ".idea_modules"

