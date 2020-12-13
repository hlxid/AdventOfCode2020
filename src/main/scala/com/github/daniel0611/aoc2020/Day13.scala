package com.github.daniel0611.aoc2020

import scala.annotation.tailrec

case class Bus(id: Int) {
  def isAtStop(time: Long): Boolean = time % id == 0
}

case class BusSchedule(currentTime: Long, lines: List[Option[Bus]]) {
  private val inServiceLines = lines.filter(_.isDefined).flatten

  @tailrec
  final def getNextBus(time: Long = currentTime): (Bus, Long) = {
    val bus = inServiceLines.find(_.isAtStop(time))
    if (bus.isDefined) (bus.get, time)
    else getNextBus(time + 1)
  }
}

object Day13 extends AoCChallenge[BusSchedule, Long] {
  override def day: Int = 13

  override def parsePuzzleInput(input: List[String]): BusSchedule = BusSchedule(
    currentTime = input.head.toInt,
    lines = input.last.split(",")
      .map(_.toIntOption.map(Bus))
      .toList
  )


  override def runA(input: BusSchedule): Long = {
    val (bus, arrival) = input.getNextBus()
    bus.id * (arrival - input.currentTime)
  }

  override def runB(input: BusSchedule): Long = {
    val l = input.lines.zipWithIndex.filter(_._1.isDefined).map(x => (x._1.get.id, x._2))
    // not idiomatic but this took way too long
    var time = 0L // could be optimized by using the provided start value, but that won't work for the tests which result in smaller values
    var incrementStep = l.head._1.toLong // common divider of previous busses
    for(i <- 1 until l.size) {
      // increment time till bus at i meets pattern ((time + shift) mod id = 0)
      while(((time + l(i)._2) % l(i)._1) != 0) {
        time += incrementStep // lets to a step and then re-check
      }
      incrementStep *= l(i)._1 // update step size with new common divider
    }
    time
  }
}
