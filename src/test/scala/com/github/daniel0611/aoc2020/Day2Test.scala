package com.github.daniel0611.aoc2020

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should

class Day2Test extends AnyFlatSpec with should.Matchers {
  val testInput = List(
    PasswordPolicy('a', 1, 3, "abcde"),
    PasswordPolicy('b', 1, 3, "cdefg"),
    PasswordPolicy('c', 2, 9, "ccccccccc"),
  )

  "A" should "correctly compute the given sample" in {
    Day2.runA(testInput) should be(2)
  }

  "B" should "correctly compute the given sample" in {
    Day2.runB(testInput) should be(1)
  }
}
