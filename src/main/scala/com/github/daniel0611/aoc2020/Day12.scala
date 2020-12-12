package com.github.daniel0611.aoc2020

import scala.annotation.tailrec

sealed trait Direction {
  def right(): Direction
  def left(): Direction
}

case class North() extends Direction {
  override def right(): Direction = East()
  override def left(): Direction = West()
}

case class East() extends Direction {
  override def right(): Direction = South()
  override def left(): Direction = North()
}

case class South() extends Direction {
  override def right(): Direction = West()
  override def left(): Direction = East()
}

case class West() extends Direction {
  override def right(): Direction = North()
  override def left(): Direction = South()
}

case class Forward() extends Direction {
  override def right(): Direction = null
  override def left(): Direction = null
}

case class Left() extends Direction {
  override def right(): Direction = null
  override def left(): Direction = null
}

case class Right() extends Direction {
  override def right(): Direction = null
  override def left(): Direction = null
}

case class NavInstruction(private val direction: Direction, amount: Int) {
  def getOffsetA(facing: Direction): (Int, Int, Direction) = direction match {
    case North() => (amount, 0, facing)
    case East() => (0, amount, facing)
    case South() => (-amount, 0, facing)
    case West() => (0, -amount, facing)
    case Forward() => copy(direction = facing).getOffsetA(facing)
    case d => (0, 0, rotateDirection(facing, d, amount))
  }

  def applyOffsetB(current: (Int, Int, Direction, Int, Int)): (Int, Int, Direction, Int, Int) = direction match {
    case North() => current.copy(_4 = current._4 + amount)
    case East() => current.copy(_5 = current._5 + amount)
    case South() => current.copy(_4 = current._4 - amount)
    case West() => current.copy(_5 = current._5 - amount)
    case Forward() => current.copy(_1 = current._1 + current._4 * amount, _2 = current._2 + current._5 * amount)
    case Left() => applyRotation(current, left = true, amount)
    case Right() => applyRotation(current, left = false, amount)
  }

  @tailrec
  private def rotateDirection(facing: Direction, rotationDirection: Direction, degrees: Int): Direction = rotationDirection match {
    case Left() if degrees > 90 => rotateDirection(facing.left(), rotationDirection, degrees - 90)
    case Left() => facing.left()
    case Right() if degrees > 90 => rotateDirection(facing.right(), rotationDirection, degrees - 90)
    case Right() => facing.right()
    case _ => facing
  }

  @tailrec
  private def applyRotation(point: (Int, Int, Direction, Int, Int), left: Boolean, degrees: Int): (Int, Int, Direction, Int, Int) = {
    val applied = if (left)
      point.copy(_4 = point._5, _5 = -point._4)
    else
      point.copy(_4 = -point._5, _5 = point._4)
    if (degrees > 90) applyRotation(applied, left, degrees - 90)
    else applied
  }
}


object Day12 extends AoCChallenge[List[NavInstruction], Int] {
  override def day: Int = 12

  override def parsePuzzleInput(input: List[String]): List[NavInstruction] =
    input.map(line => (line.head, line.drop(1).toInt))
      .map {
        case ('N', a) => (North(), a)
        case ('E', a) => (East(), a)
        case ('S', a) => (South(), a)
        case ('W', a) => (West(), a)
        case ('F', a) => (Forward(), a)
        case ('L', a) => (Left(), a)
        case ('R', a) => (Right(), a)
        case _ => (North(), 0)
      }
      .map(t => NavInstruction(t._1, t._2))

  override def runA(input: List[NavInstruction]): Int = {
    val endPos = input.foldLeft[(Int, Int, Direction)]((0, 0, East()))((acc, instr) => {
      val offset = instr.getOffsetA(acc._3)
      (acc._1 + offset._1, acc._2 + offset._2, offset._3)
    })
    endPos._1.abs + endPos._2.abs
  }

  override def runB(input: List[NavInstruction]): Int = {
    val endPos = input.foldLeft[(Int, Int, Direction, Int, Int)]((0, 0, East(), 1, 10))((acc, instr) => {
      instr.applyOffsetB(acc)
    })
    endPos._1.abs + endPos._2.abs
  }
}
