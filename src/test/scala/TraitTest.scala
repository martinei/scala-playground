import org.junit.Test
import sun.jvm.hotspot.debugger.cdbg.BaseClass

class TraitTest {



  class BaseClass {
    var baseClassMember: String = "defined in BaseClass";
  }

  trait Trait1 extends BaseClass {
    // Traits can have state
    var trait1Member =3
    baseClassMember = "defined in Trait1";


    def sayHello={
      println("Hello from "+ this.getClass)
      println("BaseClassMemeber is: "+baseClassMember);
      println("trait1Member is: "+trait1Member);
    }
  }

  trait Trait2 extends BaseClass {
    baseClassMember = "defined in Trait2";
    def sayGoodBy={
      println("Goodby!")
    }

    def sayHello={
      println ("I also say hello!")
    }
  }

  class SomeClass extends BaseClass with Trait1 with Trait2  {
    override def sayHello = super[Trait1].sayHello;
  }

  class SomeClassTrait2First extends BaseClass with Trait2 with Trait1  {
    override def sayHello = super[Trait1].sayHello;
  }

  class SomeClass2 () extends BaseClass with Trait1 with Trait2  {
    baseClassMember = "Overriden in  SomeClass2";
    trait1Member = 42;
    override def sayHello = super[Trait1].sayHello;
  }



  @Test
  def testUseTraitOnItsOwn()={
    val tr = new Trait1 {};
    tr.sayHello;
  }

  @Test
   def test()={
    // Use a trait on its own

    var cl = new SomeClass();

    cl.sayHello;
    cl.sayGoodBy;



    var cl2 = new SomeClassTrait2First();

    cl2.sayHello;
    cl2.sayGoodBy;

    // Its an instance of everythingh...
    println (cl.isInstanceOf[Trait1])
    println (cl.isInstanceOf[Trait2])
    println (cl.isInstanceOf[BaseClass])

  }


  @Test
  def overrideTraitMember()={
    var cl2= new SomeClass2();
    cl2.sayHello;
  }

}
