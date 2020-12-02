package com.github.daniel0611.aoc2020

case class PasswordPolicy(char: Char, left: Int, right: Int, password: String) {
  def matchesPolicyOld(): Boolean = {
    val count = password.count(_ == char)
    count <= right && count >= left
  }

  def matchesPolicyNew(): Boolean = password(left - 1) == char ^ password(right - 1) == char
}

object Day2 extends AoCChallenge[List[PasswordPolicy], Int] {
  private val passwordPolicyRegex = "([0-9]+)-([0-9]+) (.): ([a-z]+)".r

  override def day = 2

  override def getPuzzleInput: List[PasswordPolicy] =
    readInput()
      .split("\\n")
      .flatMap {
        case passwordPolicyRegex(left, right, char, password) =>
          Some(PasswordPolicy(char.head, left.toInt, right.toInt, password))
        case t =>
          println(s"Invalid: $t")
          None
      }
      .toList

  override def runA(list: List[PasswordPolicy]): Int = {
    list.count(_.matchesPolicyOld())
  }

  override def runB(list: List[PasswordPolicy]): Int = {
    list.count(_.matchesPolicyNew())
  }
}
