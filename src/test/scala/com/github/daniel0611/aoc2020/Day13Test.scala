package com.github.daniel0611.aoc2020

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should

class Day13Test extends AnyFlatSpec with should.Matchers {
  private val input =
    """939
      |7,13,x,x,59,x,31,19""".stripMargin.split("\\n").toList

  "A" should "correctly compute the given sample" in {
    Day13.runA(Day13.parsePuzzleInput(input)) should be(295)
  }

  "B" should "correctly compute the given sample" in {
    Day13.runB(Day13.parsePuzzleInput(input)) should be(1068781)
    Day13.runB(Day13.parsePuzzleInput(List("0", "17,x,13,19"))) should be(3417)
    Day13.runB(Day13.parsePuzzleInput(List("0", "67,7,59,61"))) should be(754018)
    Day13.runB(Day13.parsePuzzleInput(List("0", "67,x,7,59,61"))) should be(779210)
    Day13.runB(Day13.parsePuzzleInput(List("0", "67,7,x,59,61"))) should be(1261476)
    Day13.runB(Day13.parsePuzzleInput(List("0", "1789,37,47,1889"))) should be(1202161486)
  }
}
