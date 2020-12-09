package com.github.daniel0611.aoc2020

object Day9 extends AoCChallenge[(List[Long], Int), Long] {

  private def findPairs(num: Long, previous: List[Long]) =
    for (a <- previous; b <- previous if a != b && a + b == num) yield (a, b)

  override def day: Int = 9

  override def parsePuzzleInput(input: List[String]): (List[Long], Int) = (input.map(_.toLong), 25)

  override def runA(input: (List[Long], Int)): Long = {
    val (data, preambleLen) = input
    val (num, _) = data.zipWithIndex.find(d => {
      val (num, index) = d
      val lowerIndex = index - preambleLen
      if (lowerIndex < 0) false
      else {
        val previousNumbers = Range(lowerIndex, index).map(data).toList
        findPairs(num, previousNumbers).isEmpty
      }

    }).get
    num
  }

  override def runB(input: (List[Long], Int)): Long = {
    val data = input._1
    val invalidNumber = runA(input)

    val indicesPairs = for (lower <- data.indices; higher <- (lower + 1) until data.size) yield (lower, higher)
    indicesPairs
      .filter(p => data.slice(p._1, p._2 + 1).sum == invalidNumber)
      .map(p => data.slice(p._1, p._2 + 1).sorted)
      .map(s => s.head + s.last).head
  }
}
