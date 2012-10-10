import org.junit.Test

class PlaygroundTest {


  @Test
  def caseJustDefinesAFunction {
    var foo: Any => String = null

    foo = {
      case 1 => "One"
      case _ => "Not one"
    }

    println(foo(1));
    println(foo(2));
  }


  // Using match with case

  def matchTest(x: Int): String = x match {
    case 1 => "one"
    case 2 => "two"
    case _ => "many"
  }

  @Test
  def testMatchWithCase = {
    println(matchTest(1));
    println(matchTest(2));
    println(matchTest(3));
  }


  // Matching with Types
  def matchAny(x: Any): String = x match {
    case 1 => "one"
    case "eins" => "one"
    case y: Int => "Just another int: " + y
    case _ => "unkown"
  }

  @Test
  def testTypedCase = {
    println(matchAny("eins"))
    println(matchAny(1))
    println(matchAny(2))
    println(matchAny("foo"))
  }


  // The unapply Method

  object Even {
    def apply(x: Int): Int = {
      return x * 2
    }

    // Look Ma! This is overloaded!
    def unapply(z: Int): Boolean = {
      z % 2 == 0
    }

    def unapply(z: String): Boolean = {
      z == "2";
    }

  }

  @Test
  def testUnapply = {
    val x: Int = 22
    x match {

      // boolean result = Even.unapply (x)
      // if result..
      case Even() => Console.println("Grade")
    } // prints 21

    "2" match {
      case Even() => Console.println("Grade")
    } // prints 21
  }


  object Even2 {
    def unapply(z: Int): Option[Int] = {
      if (z % 2 == 0) return Some(z / 2) else return None;
    }
  }

  @Test
  def testUnapply2 = {
    var x: Int = 32
    x match {
      case Even2(z) => Console.println(x + " is twice " + z);
    }

    x = 8
    x match {
      case Even2(Even2(n)) => Console.println(x + " is a 4 times " + n)
      case _ => println("Not a multiple")
    } // prints 21
  }

  // You can have multiple parameter, introducing compainion objects

  class MyPair(val x: Int, val y: Int) {
  }

  object MyPair {
    def unapply(pair: MyPair): Option[(Int, Int)] = {
      Some((pair.x, pair.y))
    }
  }


  @Test
  def testSelfMadePair = {
    val p = new MyPair(1, 3);
    p match {
      case MyPair(x: Int, y: Int) => println("A Pair with " + x + " and " + y)
    }
  }


  // Case Classes are just syntactic sugar for generating the companion object with apply, unapplu and
  // a bunch of other stuff

  case class Pair(a: Int, b: Int);

  @Test
  def testCase = {
    val p = Pair(2, 5)
    p match {
      case Pair(x, y) => println("A Pair with " + x + " and " + y)
    }
  }
}
