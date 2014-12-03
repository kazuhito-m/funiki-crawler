seq(conscriptSettings :_*)

organization := "hm.orz.sumpic"

name := "funiki-crawler"

version := "0.1.0"

scalaVersion := "2.11.1"

publishTo := Some(Resolver.file("funiki-crawler",file("./"))(Patterns(true, Resolver.mavenStyleBasePattern)))
