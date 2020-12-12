package com.github.daniel0611.aoc2020

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should

class Day12Test extends AnyFlatSpec with should.Matchers {
  private val input =
    """F10
      |N3
      |F7
      |R90
      |F11""".stripMargin.split("\\n").toList

  "A" should "correctly compute the given sample" in {
    Day12.runA(Day12.parsePuzzleInput(input)) should be(25)
  }

  "B" should "correctly compute the given sample" in {
    Day12.runB(Day12.parsePuzzleInput(input)) should be(286)
  }
}
