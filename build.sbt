seq(conscriptSettings :_*)

organization := "hm.orz.sumpic"

name := "funiki-crawler"

version := "0.1.0"

publishTo := Some(Resolver.file("funiki-crawler",file("../repo/"))(Patterns(true, Resolver.mavenStyleBasePattern)))
