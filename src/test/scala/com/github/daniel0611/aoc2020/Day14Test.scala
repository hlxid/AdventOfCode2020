package com.github.daniel0611.aoc2020

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should

class Day14Test extends AnyFlatSpec with should.Matchers {
  private val input1 =
    """mask = XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X
      |mem[8] = 11
      |mem[7] = 101
      |mem[8] = 0""".stripMargin.split("\\n").toList

  private val input2 = """mask = 000000000000000000000000000000X1001X
                         |mem[42] = 100
                         |mask = 00000000000000000000000000000000X0XX
                         |mem[26] = 1""".stripMargin.split("\\n").toList

  "A" should "correctly compute the given sample" in {
    Day14.runA(Day14.parsePuzzleInput(input1)) should be(165L)
  }

  "B" should "correctly compute the given sample" in {
    Day14.runB(Day14.parsePuzzleInput(input2)) should be(208L)
  }
}
