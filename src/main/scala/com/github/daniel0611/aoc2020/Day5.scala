package com.github.daniel0611.aoc2020

case class BoardingPass(str: String) {

  def getRow: Int = {
    str.take(7).foldLeft(0)((acc, char) => {
      if (char == 'B') (acc + 1) << 1
      else acc << 1
    }) >> 1
  }

  def getColumn: Int = {
    str.takeRight(3).foldLeft(0)((acc, char) => {
      if (char == 'R') (acc + 1) << 1
      else acc << 1
    }) >> 1
  }

  def getSeatID: Int = getRow * 8 + getColumn
}

object Day5 extends AoCChallenge[List[BoardingPass], Int] {
  override def day: Int = 5

  override def parsePuzzleInput(input: List[String]): List[BoardingPass] = input.map(BoardingPass)

  override def runA(input: List[BoardingPass]): Int = input.map(_.getSeatID).max

  override def runB(input: List[BoardingPass]): Int = {
    val sorted = input.map(_.getSeatID).sorted
    sorted.fold(sorted.head - 1)((previous, current) => {
      if (previous + 1 != current) return previous + 1
      else current
    })
  }
}
