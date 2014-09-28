package de.digitalculture.scalaeval

object TestEngine extends App {
  import scala.collection.JavaConversions._

  val manager = new javax.script.ScriptEngineManager
  val factories = manager.getEngineFactories()
  println(factories.mkString("\n"))

}