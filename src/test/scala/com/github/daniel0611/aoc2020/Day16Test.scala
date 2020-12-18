package com.github.daniel0611.aoc2020

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should

class Day16Test extends AnyFlatSpec with should.Matchers {
  private val input =
    """class: 1-3 or 5-7
      |row: 6-11 or 33-44
      |seat: 13-40 or 45-50
      |
      |your ticket:
      |7,1,14
      |
      |nearby tickets:
      |7,3,47
      |40,4,50
      |55,2,20
      |38,6,12""".stripMargin.split("\\n").toList


  "A" should "correctly compute the given sample" in {
    Day16.runA(Day16.parsePuzzleInput(input)) should be(71)
  }
}
