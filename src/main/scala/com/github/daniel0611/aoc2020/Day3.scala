package com.github.daniel0611.aoc2020

object Day3 extends AoCChallenge[List[List[Boolean]], Long] {

  override def day: Int = 3

  override def parsePuzzleInput(in: List[String]): List[List[Boolean]] = {
    in.map(_.toCharArray.toList.map(_ == '#'))
  }

  override def runA(map: List[List[Boolean]]): Long = walkTreeMap(map, 3, 1)

  def walkTreeMap(map: List[List[Boolean]], right: Int, down: Int, x: Int = 0, y: Int = 0): Long = {
    val nextY = y + down
    val nextX = (x + right) % map.head.size

    if (nextY >= map.size) 0
    else walkTreeMap(map, right, down, nextX, nextY) + (if (map(nextY)(nextX)) 1 else 0)
  }

  override def runB(map: List[List[Boolean]]): Long = {
    List((1, 1), (3, 1), (5, 1), (7, 1), (1, 2))
      .map(slope => walkTreeMap(map, slope._1, slope._2))
      .product
  }
}
