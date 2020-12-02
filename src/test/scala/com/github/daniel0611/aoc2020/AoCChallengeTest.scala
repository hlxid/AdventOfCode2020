package com.github.daniel0611.aoc2020

import org.scalatest.flatspec._
import org.scalatest.matchers.should

class AoCChallengeTest extends AnyFlatSpec with should.Matchers with AoCChallenge[Unit, Boolean] {
  override def run(p: Unit) = false
  override def getDefaultPuzzleInput: Unit = ()

  it should "read a file in the input directory correctly" in {
    readInput(0) should be("test123\ntest456")
  }
}
