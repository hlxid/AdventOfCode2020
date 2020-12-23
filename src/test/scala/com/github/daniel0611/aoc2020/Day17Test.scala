package com.github.daniel0611.aoc2020

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should

class Day17Test extends AnyFlatSpec with should.Matchers {
  private val input =
    """.#.
      |..#
      |###""".stripMargin.split("\\n").toList


  "A" should "correctly compute the given sample" in {
    Day17.runA(Day17.parsePuzzleInput(input)) should be(112)
  }

  "B" should "correctly compute the given sample" in {
    Day17.runB(Day17.parsePuzzleInput(input)) should be(848)
  }
}
