package com.github.daniel0611.aoc2020

import scala.annotation.tailrec

sealed case class Seat(char: Char) {
  def isOccupied: Boolean = char == '#'
  def isFloor: Boolean = char == '.'

  def getNextState(adjacent: List[Seat], minSeatsToMove: Int): Seat = char match {
    case 'L' if adjacent.count(_.isOccupied) == 0 => Seat('#')
    case '#' if adjacent.count(_.isOccupied) >= minSeatsToMove => Seat('L')
    case _ => this
  }
}

case class SeatLayout(seats: List[List[Seat]]) {
  private val adjacentShift = List((-1, -1), (-1, 0), (-1, 1), (0, -1), (0, 1), (1, -1), (1, 0), (1, 1))

  private def step(nextSeatState: (Seat, (Int, Int)) => Seat): SeatLayout = {
    val indices = seats.indices.map(row => seats.head.indices.map(column => (row, column)).toList).toList
    val newSeatStates = indices.map(_.map(index => nextSeatState(seats(index._1)(index._2), index)))
    copy(newSeatStates)
  }

  def stepA(): SeatLayout = step((seat, idx) => seat.getNextState(getAdjacentSeatsA(idx), 4))

  def stepB(): SeatLayout = step((seat, idx) => seat.getNextState(getAdjacentSeatsB(idx), 5))

  private def getAdjacentSeatsA(index: (Int, Int)): List[Seat] = {
    adjacentShift
      .map(shift => (index._1 + shift._1, index._2 + shift._2))
      .filter(isValidIndex)
      .map(index => seats(index._1)(index._2))
  }

  private def isValidIndex(index: (Int, Int)): Boolean =seats.indices.contains(index._1) && seats.head.indices.contains(index._2)

  private def getAdjacentSeatsB(index: (Int, Int)): List[Seat] = {
    adjacentShift.flatMap(shift => getAdjacentSeatsBDirection(index, shift))
  }

  @tailrec
  private def getAdjacentSeatsBDirection(index: (Int, Int), shift: (Int, Int), multiplier: Int = 1): Option[Seat] = {
    val multipliedShift = (shift._1 * multiplier, shift._2 * multiplier)
    val shiftedIndex = (index._1 + multipliedShift._1, index._2 + multipliedShift._2)
    if(isValidIndex(shiftedIndex)) {
      val seat = seats(shiftedIndex._1)(shiftedIndex._2)
      if(seat.isFloor) getAdjacentSeatsBDirection(index, shift, multiplier + 1)
      else Some(seat)
    } else None
  }
}

object Day11 extends AoCChallenge[SeatLayout, Int] {
  override def day: Int = 11

  override def parsePuzzleInput(input: List[String]): SeatLayout =
    SeatLayout(input.map(row => row.toCharArray.map(Seat).toList))

  @tailrec
  override def runA(layout: SeatLayout): Int = {
    val newLayout = layout.stepA()
    if (newLayout == layout) layout.seats.flatten.count(_.isOccupied)
    else runA(newLayout)
  }

  @tailrec
  override def runB(layout: SeatLayout): Int = {
    val newLayout = layout.stepB()
    if (newLayout == layout) layout.seats.flatten.count(_.isOccupied)
    else runB(newLayout)
  }
}
