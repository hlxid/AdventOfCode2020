package com.github.daniel0611.aoc2020

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should

class Day1Test extends AnyFlatSpec with should.Matchers {
  val testInput = List(
    1721,
    979,
    366,
    299,
    675,
    1456,
  )

  "A" should "correctly compute the given sample" in {
    Day1.runA(testInput) should be(514579)
  }

  "B" should "correctly compute the given sample" in {
    Day1.runB(testInput) should be(241861950)
  }
}
