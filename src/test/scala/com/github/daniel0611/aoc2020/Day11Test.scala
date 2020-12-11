package com.github.daniel0611.aoc2020

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should

class Day11Test extends AnyFlatSpec with should.Matchers {
  private val input =
    """L.LL.LL.LL
      |LLLLLLL.LL
      |L.L.L..L..
      |LLLL.LL.LL
      |L.LL.LL.LL
      |L.LLLLL.LL
      |..L.L.....
      |LLLLLLLLLL
      |L.LLLLLL.L
      |L.LLLLL.LL"""".stripMargin.split("\\n").toList

  "A" should "correctly compute the given sample" in {
    Day11.runA(Day11.parsePuzzleInput(input)) should be(37)
  }

  "B" should "correctly compute the given sample" in {
    Day11.runB(Day11.parsePuzzleInput(input)) should be(26)
  }
}
