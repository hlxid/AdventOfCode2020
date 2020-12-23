package com.github.daniel0611.aoc2020

case class Coordinate(x: Int, y: Int, z: Int, w: Int) {
  private val neighborShifts = (for (x <- -1 to 1; y <- -1 to 1; z <- -1 to 1; w <- -1 to 1) yield (x, y, z, w)).toList.filterNot(_ == (0, 0, 0, 0))

  def getNeighbors(useW: Boolean): List[Coordinate] = neighborShifts.filter(sh => sh._4 == 0 || useW)
    .map(sh => Coordinate(x + sh._1, y + sh._2, z + sh._3, w + sh._4))
}

case class World(coordinates: Map[Coordinate, Boolean]) {
  def addCoord(coord: Coordinate, value: Boolean): World = copy(coordinates = coordinates + (coord -> value))

  def getSize(useW: Boolean): (Range, Range, Range, Range) = (
    getMinMaxRange(coordinates.map(_._1.x).toList),
    getMinMaxRange(coordinates.map(_._1.y).toList),
    getMinMaxRange(coordinates.map(_._1.z).toList),
    if(useW) getMinMaxRange(coordinates.map(_._1.w).toList) else 0 to 0,
  )

  private def getMinMaxRange(values: List[Int]) =
    // Widen simulation range, because inactive cubes outside that have at least one active in range are also important
    values.min - 1 to values.max + 1

  def step(useW: Boolean): World = {
    getAllCoords(useW).map(c => c.copy(_2 = nextState(c._1, c._2, useW)))
      .foldLeft(World(Map()))((acc, v) => acc.addCoord(v._1, v._2))
  }

  def getAllCoords(useW: Boolean): List[(Coordinate, Boolean)] = {
    val (xR, yR, zR, wR) = getSize(useW)
    val iter = for (x <- xR; y <- yR; z <- zR; w <- wR) yield {
      val c = Coordinate(x, y, z, w)
      c -> coordinates.getOrElse(c, false)
    }
    iter.toList
  }

  private def nextState(coord: Coordinate, value: Boolean, useW: Boolean): Boolean =
    (value, coord.getNeighbors(useW).map(c => coordinates.getOrElse(c, false)).count(_ == true)) match {
      case (true, 2) => true
      case (true, 3) => true
      case (false, 3) => true
      case _ => false
    }

  def visualize(useW: Boolean): Unit = {
    val (xR, yR, zR, wR) = getSize(useW)
    for (z <- zR; w <- wR) {
      println(s"z=$z;w=$w")
      for (y <- yR) {
        for (x <- xR) {
          val value = coordinates.getOrElse(Coordinate(x, y, z, w), false)
          print {
            if (value) '#'
            else '.'
          }
        }
        println()
      }
      println()
    }
  }
}

object Day17 extends AoCChallenge[World, Int] {
  override def day: Int = 17

  override def parsePuzzleInput(input: List[String]): World =
    input.map(_.toCharArray).zipWithIndex
      .flatMap(y => {
        y._1.zipWithIndex.map(x => Coordinate(x._2, y._2, 0, 0) -> (x._1 == '#'))
      })
      .foldLeft(World(Map()))((acc, v) => acc.addCoord(v._1, v._2))

  override def runA(input: World): Int = simulate(input, useW = false)

  override def runB(input: World): Int = simulate(input, useW = true)

  private def simulate(world: World, useW: Boolean) = {
    val w = (1 to 6).foldLeft(world)((w, i) => {
      println(w.coordinates.count(_._2 == true))
      println(s"Running step $i")
      val n = w.step(useW)
      n.visualize(useW)
      n
    })
    w.coordinates.count(_._2 == true)
  }
}
