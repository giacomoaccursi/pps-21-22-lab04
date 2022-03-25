package u04lab.polyglot.a01a
import Logics.*
import scala.util.*
import u04lab.code.List.*
import u04lab.code.*

/** solution and descriptions at https://bitbucket.org/mviroli/oop2019-esami/src/master/a01a/sol2/ */

case class Pair[A, B](x: A, y: B)

case class Attempts(var attempts: Int):
  def dec() = attempts = attempts - 1
  def maxFailureReached(): Boolean = attempts < 0

case class Boat(tableSize: Int, boatSize: Int):
  var hits: List[Pair[Int, Int]] = Nil()
  val boatRow = Random().nextInt(tableSize)
  val boatLeftCol = Random().nextInt(tableSize - boatSize + 1)
  println(s"x = $boatLeftCol, y = $boatRow")

  def hit(row: Int, col: Int): Boolean =
    val isHit: Boolean = isBoatHit(row, col) && !pointAlreadyHit(row, col)
    if (isHit) then
      hits = append(hits, Cons(Pair(row, col), Nil()))
    isHit

  def sunkenBoat(): Boolean = length(hits) == boatSize
  private def pointAlreadyHit(row: Int, col: Int): Boolean = contains(hits, Pair(row, col))
  private def isBoatHit(row: Int, col: Int): Boolean = row == boatRow && col >= boatLeftCol && col < (boatLeftCol + boatSize)

class LogicsImpl(private val tableSize: Int, private val boatSize: Int) extends Logics:
  val attempts: Attempts = Attempts(5)
  val boat: Boat = Boat(tableSize, boatSize)

  def hit(row: Int, col: Int) =
    if boat.hit(row, col) then
      if boat.sunkenBoat() then Result.WON else Result.HIT
    else
      attempts.dec()
      if attempts.maxFailureReached() then Result.LOST else Result.MISS