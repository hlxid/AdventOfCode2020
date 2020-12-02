package com.github.daniel0611.aoc2020

import org.scalatest.flatspec._
import org.scalatest.matchers.should

class AoCChallengeTest extends AnyFlatSpec with should.Matchers with AoCChallenge[Unit, Boolean] {
  override def runA(p: Unit) = false
  override def runB(p: Unit) = true
  override def getPuzzleInput: Unit = ()
  override def day = 0

  it should "read a file in the input directory correctly" in {
    readInput() should be("test123\ntest456")
  }
}
