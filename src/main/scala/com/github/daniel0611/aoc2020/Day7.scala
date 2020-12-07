package com.github.daniel0611.aoc2020

object Day7 extends AoCChallenge[Map[String, List[(Int, String)]], Int] {
  override def day: Int = 7

  override def parsePuzzleInput(input: List[String]): Map[String, List[(Int, String)]] = {
    input.map(bagString => {
      val bagParts = bagString.split(" contain ")
      val name = bagParts.head.split(" ").dropRight(1).mkString(" ")
      val contains = bagParts(1)
        .replaceAll("\\.", "")
        .split(",")
        .map(_.trim)
        .map(parseContainEntry)
        .filter(_._1 != 0)

      (name, contains.toList)
    })
      .toMap
  }

  private def parseContainEntry(input: String): (Int, String) = {
    val parts = input.split(" ").dropRight(1)
    val count = if (parts.head == "no") 0 else parts.head.toInt
    val color = parts.takeRight(parts.length - 1).mkString(" ")
    (count, color)
  }

  override def runA(input: Map[String, List[(Int, String)]]): Int =
    input
      .filter(elem => elem._1 != "shiny gold") // a shiny gold bag can obviously hold a shiny gold bag, not wanted here
      .count(elem => canHoldBag(input, elem._1, "shiny gold"))

  private def canHoldBag(bags: Map[String, List[(Int, String)]], bag: String, target: String): Boolean = {
    val recipes = bags(bag)
    if(bag == target) true
    else recipes.exists(b => canHoldBag(bags, b._2, target))
  }

  override def runB(input: Map[String, List[(Int, String)]]): Int = {
    getSubBagCount(input, "shiny gold") - 1 // Don't count the shiny gold bag
  }

  private def getSubBagCount(bags: Map[String, List[(Int, String)]], searchBag: String): Int = {
    val recipes = bags(searchBag)
    if(recipes.isEmpty) 1
    else recipes.map(b => b._1 * getSubBagCount(bags, b._2)).sum + 1 // don't forget this bag
  }

}
