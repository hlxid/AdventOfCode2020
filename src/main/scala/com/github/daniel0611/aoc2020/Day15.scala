package com.github.daniel0611.aoc2020

import scala.annotation.tailrec

object Day15 extends AoCChallenge[List[Int], Int] {
  override def day: Int = 15

  override def parsePuzzleInput(input: List[String]): List[Int] =
    input.mkString.split(",").map(_.toInt).toList

  override def runA(input: List[Int]): Int = {
    runPuzzle(input, 2020)
  }

  override def runB(input: List[Int]): Int = {
    runPuzzle(input, 30000000)
  }

  @tailrec
  private def runPuzzle(state: List[Int], runTill: Int): Int = {
    if(state.size == runTill) return state.last
    val lastNum = state.last
    val distance = state.reverse.drop(1).indexOf(lastNum) + 1 // distance is one more than index, e.g. the number was the last one => index = 0, distance = 1

    runPuzzle(state :+ distance, runTill)
  }
}
