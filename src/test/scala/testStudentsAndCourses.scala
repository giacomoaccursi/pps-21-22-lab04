import org.junit.*
import org.junit.Assert.*
import u04lab.code.Complex

class testStudentsAndCourses:
  @Test def testToString() =
    val a = Array(Complex(10, 20), Complex(1, 1), Complex(7, 0))
    val c = a(0) + a(1) + a(2)
    

