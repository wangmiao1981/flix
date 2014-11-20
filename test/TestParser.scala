import impl.ast2.{Ast, Parser}
import org.scalatest.FunSuite
import scala.collection.immutable.Seq

class TestParser extends FunSuite {

  test("Parser.Type01") {
    val s = """type t = Unit;"""
    val a = Ast.Type.Unit

    assertResult(a)(getType(Parser.parse(s)))
  }

  test("Parser.Type02") {
    val s = """type t = Bool;"""
    val t = Ast.Type.Bool

    assertResult(t)(getType(Parser.parse(s)))
  }

  test("Parser.Type03") {
    val s = """type t = Int;"""
    val t = Ast.Type.Int

    assertResult(t)(getType(Parser.parse(s)))
  }

  test("Parser.Type04") {
    val s = """type t = Str;"""
    val t = Ast.Type.Str

    assertResult(t)(getType(Parser.parse(s)))
  }

  test("Parser.Type05") {
    val s = """type t = (Bool, Bool);"""
    val t = Ast.Type.Tuple(Seq(Ast.Type.Bool, Ast.Type.Bool))

    assertResult(t)(getType(Parser.parse(s)))
  }

  test("Parser.Type06") {
    val s = """type t = (Bool, Bool, Bool);"""
    val t = Ast.Type.Tuple(Seq(Ast.Type.Bool, Ast.Type.Bool, Ast.Type.Bool))

    assertResult(t)(getType(Parser.parse(s)))
  }

  test("Parser.Type07") {
    val s = """type t = (Bool, Int, Str);"""
    val t = Ast.Type.Tuple(Seq(Ast.Type.Bool, Ast.Type.Int, Ast.Type.Str))

    assertResult(t)(getType(Parser.parse(s)))
  }

  test("Parser.Type08") {
    val s = """type t = (Unit, Bool, Int, Str, Unit, Bool, Int, Str);"""
    val t = Ast.Type.Tuple(Seq(Ast.Type.Unit, Ast.Type.Bool, Ast.Type.Int, Ast.Type.Str, Ast.Type.Unit, Ast.Type.Bool, Ast.Type.Int, Ast.Type.Str))

    assertResult(t)(getType(Parser.parse(s)))
  }

  test("Parser.Type09") {
    val s = """type t = (Unit);"""
    val t = Ast.Type.Tuple(Seq(Ast.Type.Unit))

    assertResult(t)(getType(Parser.parse(s)))
  }

  private def getType(root: Ast.Root): Ast.Type = root match {
    case Ast.Root(Seq(Ast.Declaration.TypeDecl(_, typ))) => typ
    case _ => throw new RuntimeException()
  }

}