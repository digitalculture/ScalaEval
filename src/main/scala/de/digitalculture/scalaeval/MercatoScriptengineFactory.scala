package de.digitalculture.scalaeval

import javax.script.ScriptEngineFactory
import scala.collection.JavaConversions._
import scala.collection.mutable.Buffer

object MercatoScriptengineFactory extends ScriptEngineFactory {

  private val ENGINE_NAME = "Mercato Script Engine Factory";

  private val ENGINE_VERSION = "0.1";

  private val ENGINE_EXTENSIONS = Buffer("scala");

  private val ENGINE_MIME_TYPES = Buffer("application/scala");

  private val ENGINE_NAMES = Buffer("scala");

  private val ENGINE_LANGUANGE_NAME = "scala";

  private val ENGINE_LANGUAGE_VERSION = "2.11";

  override def getEngineName = ENGINE_NAME

  override def getEngineVersion = ENGINE_VERSION

  override def getExtensions = ENGINE_EXTENSIONS

  override def getMimeTypes = ENGINE_MIME_TYPES

  override def getNames = ENGINE_NAMES

  override def getLanguageName = ENGINE_LANGUANGE_NAME

  override def getLanguageVersion = ENGINE_LANGUAGE_VERSION

  override def getParameter(key: String) = ""

  override def getMethodCallSyntax(obj: String, m: String, args: String*) =
    "%s.%s(%s)".format(obj, m, args.mkString(", "))

  override def getOutputStatement(toDisplay: String) = "println(%s)".format(toDisplay)

  override def getProgram(statements: String*) = statements.mkString("\n")

  override def getScriptEngine = new MercatoScriptEngine

}
