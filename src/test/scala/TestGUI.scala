import org.junit.*
import org.junit.Assert.*
import u04lab.code.List.*
import u04lab.polyglot.a01a.{Logics, LogicsImpl}
import u04lab.polyglot.a01a.Logics.*

//@main def startGui(): Unit = new GUI(4, 2)

class testGui:

  val size = 5
  val boatSize = 2
  val logics = new LogicsImpl(size, boatSize)
  val boatRow = logics.boat.boatRow
  val boatLeftCol = logics.boat.boatLeftCol

  @Test def testEmptyHitsOnStart() =
    assertEquals(0, length(logics.boat.hits))

  @Test def testHit() =
    assertEquals(Logics.Result.HIT, logics.hit(boatRow, boatLeftCol))

  @Test def testMiss() =
    assertEquals(Logics.Result.MISS, logics.hit((boatRow + 1) % size , boatLeftCol))

  @Test def testWon() =
    logics.hit(boatRow, boatLeftCol)
    assertEquals(Logics.Result.WON, logics.hit(boatRow, boatLeftCol + 1))
    assertEquals(boatSize, length(logics.boat.hits))

  @Test def testLost() =
    (1 to logics.attempts.attempts).foreach(i => logics.hit((boatRow + 1) % size , boatLeftCol))
    assertEquals(Logics.Result.LOST, logics.hit((boatRow + 1) % size , boatLeftCol))



