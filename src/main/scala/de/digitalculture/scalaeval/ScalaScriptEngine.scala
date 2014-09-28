package de.digitalculture.scalaeval

import java.io.{ Reader, StringWriter }
import java.util.Map.Entry
import javax.script.{ AbstractScriptEngine, Bindings, ScriptContext, ScriptEngineFactory, ScriptException, SimpleBindings }
import scala.util.control.NonFatal
import com.google.common.base.Charsets
import com.google.common.io.CharStreams
import java.io.InputStreamReader

class ScalaScriptEngine extends AbstractScriptEngine {

  var bindings = new SimpleBindings

  val sourceDir = ""

  //  val sse = ScalaScriptEngine.onChangeRefresh(new File(sourceDir))

  override def createBindings: Bindings = {
    this.bindings = new SimpleBindings
    bindings
  }

  @throws[ScriptException]("if the script emits an error")
  @throws[NullPointerException]("if an argument value is null")
  override def eval(reader: Reader, context: ScriptContext): Object = {
    val string = CharStreams.toString(reader)

    eval(string, context)
  }

  //  @throws[ScriptException]("if the script emits an error")
  //  @throws[NullPointerException]("if an argument value is null")
  //  override def eval(script: String, context: ScriptContext): Object = {
  //    val bindEntries = bindings.entrySet.toArray(Array.empty[Entry[String, Object]])
  //    bindings.size match {
  //      case 0 => toJavaType(EvalCode.withoutArgs[Any](script).newInstance.apply)
  //      case 1 => toJavaType(EvalCode.with1Arg[Object, Any](bindEntries(0).getKey, script).newInstance.apply(bindEntries(0).getValue))
  //      case 2 => toJavaType(EvalCode.with2Args[Object, Object, Any](bindEntries(0).getKey, bindEntries(1).getKey, script).newInstance.apply(bindEntries(0).getValue, bindEntries(0).getValue))
  //      case 3 => toJavaType(EvalCode.with3Args[Object, Object, Object, Any](bindEntries(0).getKey, bindEntries(1).getKey, bindEntries(2).getKey, script).newInstance.apply(bindEntries(0).getValue(), bindEntries(1).getValue(), bindEntries(2).getValue()))
  //      case _ => throw new ScriptException("Cannot use more than 3 parameters!")
  //    }
  //  }

  @throws[ScriptException]("if the script emits an error")
  @throws[NullPointerException]("if an argument value is null")
  override def eval(script: String, context: ScriptContext): Object = {
    import scala.reflect.runtime.universe._
    import scala.tools.reflect.ToolBox
    val tb = runtimeMirror(this.getClass.getClassLoader).mkToolBox()
    val bindEntries = bindings.entrySet.toArray(Array.empty[Entry[String, Object]])
    val tree = tb.parse(script)
    //println(showRaw(tree))
    try {
      val fun = tb.compile(tree)
      val any = fun()
      toJavaType(any)
    } catch {
      case NonFatal(e) =>
        throw new ScriptException(s"Failed to execute script.\nScript:\n$script\n\nMessage: ${e.getMessage}", "", 0)
    }
  }

  def toJavaType(scalaType: Any): AnyRef = {

    scalaType match {
      case i: Int => new java.lang.Integer(i)
      case s: Short => new java.lang.Short(s)
      case f: Float => new java.lang.Float(f)
      case d: Double => new java.lang.Double(d)
      case c: Char => new java.lang.Character(c)
      case l: Long => new java.lang.Long(l)
      case t: Traversable[Any] => toJavaType(t)
      case o: Object => o
      case a => throw new ScriptException("Null Result!")
    }
  }

  def toJavaType(traversable: Traversable[Any]): AnyRef = {
    import scala.collection.JavaConverters._
    traversable match {
      case seq: Seq[Any] => seq.asJava
      case map: Map[_, _] => map.asJava
      case set: Set[Any] => set.asJava
      case it: Iterable[Any] => it.asJava
      case a => a
    }
  }

  override def getFactory: ScriptEngineFactory = new de.digitalculture.scalaeval.ScalaScriptEngineFactory()

}
