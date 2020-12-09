package com.github.daniel0611.aoc2020

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should

class Day9Test extends AnyFlatSpec with should.Matchers {
  private val input = (List[Long](35, 20, 15, 25, 47, 40, 62, 55, 65, 95, 102, 117, 150, 182, 127, 219, 299, 277, 309, 576), 5)

  "A" should "correctly compute the given sample" in {
    Day9.runA(input) should be(127)
  }

  "B" should "correctly compute the given sample" in {
    Day9.runB(input) should be(62)
  }
}
