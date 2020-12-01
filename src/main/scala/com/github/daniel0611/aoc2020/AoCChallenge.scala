package com.github.daniel0611.aoc2020

import scala.io.Source

trait AoCChallenge[P, R] {
  protected def readInput(path: String): String = {
    val file = Source.fromFile(s"input/$path")
    val content = file.getLines().mkString("\n")
    file.close()
    content
  }

  def run(parameter: P): R
}
