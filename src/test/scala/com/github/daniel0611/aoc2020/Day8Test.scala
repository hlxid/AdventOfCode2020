package com.github.daniel0611.aoc2020

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should

class Day8Test extends AnyFlatSpec with should.Matchers {
  private val input =
    """nop +0
      |acc +1
      |jmp +4
      |acc +3
      |jmp -3
      |acc -99
      |acc +1
      |jmp -4
      |acc +6""".stripMargin.split("\\n").toList

  "A" should "correctly compute the given sample" in {
    Day8.runA(Day8.parsePuzzleInput(input)) should be(5)
  }

  "B" should "correctly compute the given sample" in {
    Day8.runB(Day8.parsePuzzleInput(input)) should be(8)
  }
}
