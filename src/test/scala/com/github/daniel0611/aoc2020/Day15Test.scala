package com.github.daniel0611.aoc2020

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should

class Day15Test extends AnyFlatSpec with should.Matchers {
  private val input =
    """0,3,6""".stripMargin.split("\\n").toList


  "A" should "correctly compute the given sample" in {
    Day15.runA(Day15.parsePuzzleInput(input)) should be(436)
  }

  // B is not tested due to performance reasons
}
