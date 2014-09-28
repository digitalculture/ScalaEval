package de.digitalculture.scalaeval;

import javax.script.ScriptEngine;
import java.util.LinkedList;
import java.util.List;

public class ScalaScriptEngineFactory implements
		javax.script.ScriptEngineFactory {

	@Override
	public String getEngineName() {
		return "Scala Script Engine Factory";
	}

	@Override
	public String getEngineVersion() {
		return "1.0";
	}

	@Override
	public List<String> getExtensions() {
		return asList("scala");
	}

	@Override
	public List<String> getMimeTypes() {
		return asList("application/scala");
	}

	@Override
	public List<String> getNames() {
		return asList("scala2");
	}

	@Override
	public String getLanguageName() {
		return "scala";
	}

	@Override
	public String getLanguageVersion() {
		return "2.11.x";
	}

	@Override
	public Object getParameter(final String key) {
		return key;
	}

	@Override
	public String getMethodCallSyntax(final String obj, final String m,
			final String... args) {
		return "";
	}

	@Override
	public String getOutputStatement(final String toDisplay) {
		return toDisplay;
	}

	@Override
	public String getProgram(final String... statements) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ScriptEngine getScriptEngine() {
		return new ScalaScriptEngine();
	}
	// private val ENGINE_NAME = "Scala Script Engine Factory";
	//
	// private val ENGINE_VERSION = "0.1";
	//
	// private val ENGINE_EXTENSIONS = Buffer("scala");
	//
	// private val ENGINE_MIME_TYPES = Buffer("application/scala");
	//
	// private val ENGINE_NAMES = Buffer("scala");
	//
	// private val ENGINE_LANGUANGE_NAME = "scala";
	//
	// private val ENGINE_LANGUAGE_VERSION = "2.11";
	//
	// override def getEngineName = ENGINE_NAME
	//
	// override def getEngineVersion = ENGINE_VERSION
	//
	// override def getExtensions = ENGINE_EXTENSIONS
	//
	// override def getMimeTypes = ENGINE_MIME_TYPES
	//
	// override def getNames = ENGINE_NAMES
	//
	// override def getLanguageName = ENGINE_LANGUANGE_NAME
	//
	// override def getLanguageVersion = ENGINE_LANGUAGE_VERSION
	//
	// override def getParameter(key: String) = ""
	//
	// override def getMethodCallSyntax(obj: String, m: String, args: String*) =
	// "%s.%s(%s)".format(obj, m, args.mkString(", "))
	//
	// override def getOutputStatement(toDisplay: String) =
	// "println(%s)".format(toDisplay)
	//
	// override def getProgram(statements: String*) = statements.mkString("\n")
	//
	// override def getScriptEngine = new ScalaScriptEngine

	public static List<String> asList(final String s) {
		final LinkedList<String> result = new LinkedList<String>();
		result.add(s);
		return result;
	}
}
