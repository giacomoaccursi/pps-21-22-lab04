package u04lab.code

import List.*

trait Student:
  def name: String
  def year: Int
  def enrolling(courses: Course*): Unit // the student participates to a Course
  def courses: List[String] // names of course the student participates to
  def hasTeacher(teacher: String): Boolean // is the student participating to a course of this teacher?

trait Course:
  def name: String
  def teacher: String

object SameTeacher:
  def unapply(courses: List[Course]): scala.Option[String] = courses match
    case Cons(h, t) if length(courses) == length(filter(courses)(_.teacher == h.teacher)) => scala.Option(h.teacher)
    case _ => scala.Option.empty

object Student:
  def apply(name: String, year: Int = 2017): Student = StudentImpl(name, year)
  private case class StudentImpl(name: String,
                                 year: Int) extends Student:
    var coursesList: List[Course] = Nil()
    override def enrolling(courses: Course*): Unit = courses.foreach(course => if !contains(coursesList, course) then coursesList = append(coursesList, Cons(course, Nil())))
    override def courses: List[String] = map(coursesList)(_.name)
    override def hasTeacher(teacher: String): Boolean = contains(map(coursesList)(_.teacher), teacher)

object Course:
  def apply(name: String, teacher: String): Course = CourseImpl(name, teacher)
  private case class CourseImpl(override val name: String,
                                override val teacher: String) extends Course

@main def checkStudents(): Unit =
  val cPPS = Course("PPS", "Viroli")
  val cPCD = Course("PCD", "Ricci")
  val cSDR = Course("SDR", "D'Angelo")
  val s1 = Student("mario", 2015)
  val s2 = Student("gino", 2016)
  val s3 = Student("rino") // defaults to 2017
  s1.enrolling(cPPS, cPCD)
  s2.enrolling(cPPS)
  s3.enrolling(cPPS, cPCD)
  s3.enrolling(cPCD)
  s3.enrolling(cSDR)
  println(
    (s1.courses, s2.courses, s3.courses)
  ) // (Cons(PCD,Cons(PPS,Nil())),Cons(PPS,Nil()),Cons(SDR,Cons(PCD,Cons(PPS,Nil()))))
  println(s1.hasTeacher("Ricci")) // true
  s1.enrolling()

  val courses = List(cPPS, cPCD, cSDR)
  courses match
    case SameTeacher(t) => println(s" $courses have same teacher $t")
    case _ => println ( s" $courses have different teachers ")
    
/** Hints:
  *   - simply implement Course, e.g. with a case class
  *   - implement Student with a StudentImpl keeping a private Set of courses
  *   - try to implement in StudentImpl method courses with map
  *   - try to implement in StudentImpl method hasTeacher with map and find
  *   - check that the two println above work correctly
  *   - refactor the code so that method enrolling accepts a variable argument Course*
  */
