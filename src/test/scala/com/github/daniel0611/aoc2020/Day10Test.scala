package com.github.daniel0611.aoc2020

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should

class Day10Test extends AnyFlatSpec with should.Matchers {
  private val input = List(28, 33, 18, 42, 31, 14, 46, 20, 48, 47, 24, 23, 49, 45, 19, 38, 39, 11, 1, 32, 25, 35, 8, 17, 7, 9, 4, 2, 34, 10, 3)

  "A" should "correctly compute the given sample" in {
    Day10.runA(input) should be(220)
  }

  "B" should "correctly compute the given sample" in {
    Day10.runB(input) should be(19208)
  }
}
