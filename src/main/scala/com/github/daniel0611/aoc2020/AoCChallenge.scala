package com.github.daniel0611.aoc2020

import scala.io.Source

trait AoCChallenge[P, R] {
  protected def readInput(): List[String] = {
    val file = Source.fromFile(s"input/day$day.txt")
    val content = file.getLines().toList
    file.close()
    content
  }

  def parsePuzzleInput(input: List[String]) : P

  def runA(parameter: P): R

  def runB(parameter: P): R

  def day: Int

  //noinspection ScalaUnusedSymbol
  def main(args: Array[String]): Unit = {
    val in = parsePuzzleInput(readInput())
    execPuzzle(runA, in, 'A')
    execPuzzle(runB, in, 'B')
  }

  private def execPuzzle(exec: P => R, input: P, desc: Char): Unit = try {
    println(s"Day $day $desc: ${exec(input)}")
  } catch {
    case _: NotImplementedError => println(s"Day $day $desc hasn't been implemented yet.")
  }
}
