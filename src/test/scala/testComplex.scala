import org.junit.*
import org.junit.Assert.*
import u04lab.code.Complex

class testComplex:
  @Test def testToString() =
    val a = Array(Complex(10, 20), Complex(1, 1), Complex(7, 0))
    val c = a(0) + a(1) + a(2)
    assertEquals("ComplexImpl(18.0,21.0)", c.toString)
    assertEquals("18.0", c.re.toString)
    assertEquals("21.0", c.im.toString)

  @Test def testEquals() =
    assertTrue(Complex(10, 20) == Complex(10, 20))

