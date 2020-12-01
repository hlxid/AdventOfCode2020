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

  "Day1A" should "should correctly compute the given sample" in {
    Day1A.run(testInput) should be(514579)
  }

  "Day1B" should "should correctly compute the given sample" in {
    Day1B.run(testInput) should be(241861950)
  }
}
