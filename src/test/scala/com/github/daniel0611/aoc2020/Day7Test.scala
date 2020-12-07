package com.github.daniel0611.aoc2020

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should

class Day7Test extends AnyFlatSpec with should.Matchers {

  private val input1 =
    """light red bags contain 1 bright white bag, 2 muted yellow bags.
      |dark orange bags contain 3 bright white bags, 4 muted yellow bags.
      |bright white bags contain 1 shiny gold bag.
      |muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.
      |shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.
      |dark olive bags contain 3 faded blue bags, 4 dotted black bags.
      |vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.
      |faded blue bags contain no other bags.
      |dotted black bags contain no other bags.""".stripMargin.split("\\n").toList

  private val input2 =
    """shiny gold bags contain 2 dark red bags.
      |dark red bags contain 2 dark orange bags.
      |dark orange bags contain 2 dark yellow bags.
      |dark yellow bags contain 2 dark green bags.
      |dark green bags contain 2 dark blue bags.
      |dark blue bags contain 2 dark violet bags.
      |dark violet bags contain no other bags.""".stripMargin.split("\\n").toList

  "parser" should "correctly parse the given sample input" in {
    Day7.parsePuzzleInput(input1.take(2)) should be(Map(
      "light red" -> List((1, "bright white"), (2, "muted yellow")),
      "dark orange" -> List((3, "bright white"), (4, "muted yellow")),
    ))

    Day7.parsePuzzleInput(input1.takeRight(1)) should be(Map(
      "dotted black" -> List(),
    ))
  }

    "A" should "correctly compute the given sample" in {
      Day7.runA(Day7.parsePuzzleInput(input1)) should be(4)
    }

    "B" should "correctly compute the given sample" in {
      Day7.runB(Day7.parsePuzzleInput(input2)) should be(126)
    }
}
