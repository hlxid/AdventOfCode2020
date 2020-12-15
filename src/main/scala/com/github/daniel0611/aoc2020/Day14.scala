package com.github.daniel0611.aoc2020

import java.math.BigInteger
import scala.collection.mutable.ListBuffer

case class Mask(mask: List[Char]) {
  private val andMask = parseLong(mask.map {
    case '0' => 0
    case _ => 1
  }.mkString)

  private val orMask = parseLong(mask.map {
    case '1' => 1
    case _ => 0
  }.mkString)

  def maskNumber(in: Long): Long = {
    (in & andMask) | orMask
  }

  def getAddrPossibilities(addr: Long): List[Long] = {
    val possibilities = addr.toBinaryString.reverse.padTo(36, '0').reverse.toCharArray
      .zip(mask)
      .map {
        case (value, '0') => List(value)
        case (_, '1') => List('1')
        case (_, 'X') => List('0', '1')
      }
      .toList
    fold(possibilities)
  }

  private def fold(in: List[List[Char]]): List[Long] = {
    val current = ListBuffer[Char]()
    in.foreach(chars => {
      if (chars.length == 1) current += chars.head
      else {
        return chars.flatMap(c => {
          val newInput = current.toList.map(c => List(c)) ++ List(List(c)) ++ in.drop(current.size + 1)
          fold(newInput)
        })
      }
    })

    List(parseLong(in.map(_.head).mkString))
  }

  private def parseLong(in: String): Long = new BigInteger(in, 2).longValue()
}

case class MemoryState(mask: Mask, memory: Map[Long, Long])

abstract sealed class MemoryInstruction {
  def executeInstruction(input: MemoryState): MemoryState
}

case class StoreAInstruction(address: Long, value: Long) extends MemoryInstruction {
  override def executeInstruction(input: MemoryState): MemoryState =
    input.copy(memory = input.memory + (address -> input.mask.maskNumber(value)))
}

case class StoreBInstruction(address: Long, value: Long) extends MemoryInstruction {
  override def executeInstruction(input: MemoryState): MemoryState = {
    val addresses = input.mask.getAddrPossibilities(address)
    input.copy(memory = input.memory ++ addresses.map(addr => addr -> value).toMap)
  }
}

case class MaskUpdateInstruction(mask: Mask) extends MemoryInstruction {
  override def executeInstruction(input: MemoryState): MemoryState = input.copy(mask = mask)
}

object Day14 extends AoCChallenge[List[MemoryInstruction], Long] {
  private val maskRegex = "mask = ([0-9X]+)".r
  private val memRegex = "mem\\[(\\d+)] = (\\d+)".r

  override def day: Int = 14

  override def parsePuzzleInput(input: List[String]): List[MemoryInstruction] = input.map {
    case maskRegex(a) => MaskUpdateInstruction(Mask(a.toCharArray.toList))
    case memRegex(a, b) => StoreAInstruction(a.toLong, b.toLong)
  }

  override def runA(input: List[MemoryInstruction]): Long = {
    var state = MemoryState(Mask(("X" * 36).toCharArray.toList), Map())
    input.foreach(instr => state = instr.executeInstruction(state))
    state.memory.values.sum
  }

  override def runB(input: List[MemoryInstruction]): Long = {
    runA(input.map {
      case StoreAInstruction(addr, value) => StoreBInstruction(addr, value)
      case x => x
    })
  }
}
