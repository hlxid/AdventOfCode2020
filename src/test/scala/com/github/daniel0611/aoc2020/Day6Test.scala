package com.github.daniel0611.aoc2020

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should

class Day6Test extends AnyFlatSpec with should.Matchers {

  private val input =
    """abc
      |
      |a
      |b
      |c
      |
      |ab
      |ac
      |
      |a
      |a
      |a
      |a
      |
      |b""".stripMargin.split("\\n").toList

  "A" should "correctly compute the given sample" in {
    Day6.runA(Day6.parsePuzzleInput(input)) should be(11)
  }

  "B" should "correctly compute the given sample" in {
    Day6.runB(Day6.parsePuzzleInput(input)) should be(6)
  }
}
