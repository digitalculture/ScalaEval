organization := "de.digitalculture"

name := "scalaeval"

version := "1.0-SNAPSHOT"

scalaVersion := "2.11.1"

libraryDependencies += "org.scala-lang" % "scala-compiler" % "2.11.1"

libraryDependencies += "org.slf4j" % "slf4j-simple" % "1.6.4"

libraryDependencies += "joda-time" % "joda-time" % "2.2"

libraryDependencies += "javax.servlet" % "servlet-api" % "2.4"

libraryDependencies += "com.google.guava" % "guava" % "17.0"

publishTo := Some(Resolver.file("file",  new File(Path.userHome.absolutePath+"/.m2/repository")))