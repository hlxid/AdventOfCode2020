package com.github.daniel0611.aoc2020

import scala.util.matching.Regex

case class PasswordPolicy(char: Char, left: Int, right: Int, password: String) {
  def matchesPolicyOld(): Boolean = {
    val count = password.count(_ == char)
    count <= right && count >= left
  }

  def matchesPolicyNew(): Boolean = password(left - 1) == char ^ password(right - 1) == char
}

object Day2A extends AoCChallenge[List[PasswordPolicy], Int] {
  val passwordPolicyRegex: Regex = "([0-9]+)-([0-9]+) (.): ([a-z]+)".r

  override def getDefaultPuzzleInput: List[PasswordPolicy] =
    readInput(2)
      .split("\\n")
      .flatMap {
        case passwordPolicyRegex(left, right, char, password) =>
          Some(PasswordPolicy(char.head, left.toInt, right.toInt, password))
        case t =>
          println(s"Invalid: $t")
          None
      }
      .toList

  override def run(list: List[PasswordPolicy]): Int = {
    list.count(_.matchesPolicyOld())
  }
}

object Day2B extends AoCChallenge[List[PasswordPolicy], Int] {
  override def getDefaultPuzzleInput: List[PasswordPolicy] = Day2A.getDefaultPuzzleInput

  override def run(list: List[PasswordPolicy]): Int = {
    list.count(_.matchesPolicyNew())
  }
}
