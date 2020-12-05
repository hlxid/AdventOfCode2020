package com.github.daniel0611.aoc2020

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should

class Day5Test extends AnyFlatSpec with should.Matchers {

  private val input = Map(
    "FBFBBFFRLR" -> 357,
    "BFFFBBFRRR" -> 567,
    "FFFBBBFRRR" -> 119,
    "BBFFBBFRLL" -> 820,
  )

  "BoardingPass" should "correctly compute the seat id" in {
    input.foreach(v => BoardingPass(v._1).getSeatID should be(v._2))
  }

  "A" should "correctly compute the given sample" in {
    Day5.runA(Day5.parsePuzzleInput(input.keys.toList)) should be(820)
  }

//  "B" should "correctly compute the given sample" in {
//    val invalidInput = Day4.parsePuzzleInput(invalidPassports.linesIterator.toList)
//    val validInput = Day4.parsePuzzleInput(validPassports.linesIterator.toList)
//
//    Day4.runB(invalidInput) should be (0)
//    Day4.runB(validInput) should be (4)
//  }
}
