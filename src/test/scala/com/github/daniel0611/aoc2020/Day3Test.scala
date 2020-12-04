package com.github.daniel0611.aoc2020

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should

class Day3Test extends AnyFlatSpec with should.Matchers {
  private val input = """..##.......
                |#...#...#..
                |.#....#..#.
                |..#.#...#.#
                |.#...##..#.
                |..#.##.....
                |.#.#.#....#
                |.#........#
                |#.##...#...
                |#...##....#
                |.#..#...#.#""".stripMargin

  "A" should "correctly compute the given sample" in {
    Day3.runA(Day3.parsePuzzleInput(input.split("\\n").toList)) should be(7)
  }

  "B" should "correctly compute the given sample" in {
    Day3.runB(Day3.parsePuzzleInput(input.split("\\n").toList)) should be(336)
  }
}
