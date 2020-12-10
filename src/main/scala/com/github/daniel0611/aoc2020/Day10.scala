package com.github.daniel0611.aoc2020

import scala.collection.mutable

object Day10 extends AoCChallenge[List[Int], Long] {
  override def day: Int = 10

  private type Diff = (Int, Int)

  override def parsePuzzleInput(input: List[String]): List[Int] = input.map(_.toInt)

  private def getDifferences(jolts: List[Int]) = jolts.tail.zip(jolts).map(a => a._1 - a._2)

  override def runA(input: List[Int]): Long = {
    val withIntegrated = (0 :: (input.max + 3) :: input).sorted // also add integrated power adapter
    val diff = getDifferences(withIntegrated)
    diff.count(_ == 1) * diff.count(_ == 3)
  }

  val cache: mutable.HashMap[Int, Long] = new mutable.HashMap[Int, Long]()

  def countPossibilities(jolts: List[Int], index: Int): Long = {
    if (index >= jolts.length - 1)
      return 1
    var sum = 0L
    if (index + 1 <= jolts.length - 1 && jolts(index + 1) - jolts(index) <= 3)
      sum = sum + getCached(jolts, index + 1)
    if (index + 2 <= jolts.length - 1 && jolts(index + 2) - jolts(index) <= 3)
      sum = sum + getCached(jolts, index + 2)
    if (index + 3 <= jolts.length - 1 && jolts(index + 3) - jolts(index) <= 3)
      sum = sum + getCached(jolts, index + 3)
    sum
  }

  def getCached(jolts: List[Int], index: Int): Long = {
    if (!cache.contains(index))
      cache(index) = countPossibilities(jolts, index)
    cache(index)

  }

  override def runB(input: List[Int]): Long = {
    val withIntegrated = (0 :: (input.max + 3) :: input).sorted
    countPossibilities(withIntegrated, 0)
  }
}

