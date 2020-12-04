package com.github.daniel0611.aoc2020

object Day4 extends AoCChallenge[List[Map[String, String]], Int] {
  private val parsingRegex = "([a-z]{3}):([#a-z0-9]+)".r
  private val birthRange = Range.inclusive(1920, 2002)
  private val issueRange = Range.inclusive(2010, 2020)
  private val expireRange = Range.inclusive(2020, 2030)
  private val hgtInRange = Range.inclusive(59, 76)
  private val hgtCmRange = Range.inclusive(150, 193)
  private val hairRegex = "#[0-9a-f]{6}".r
  private val eclOptions = List("amb", "blu", "brn", "gry", "grn", "hzl", "oth")
  private val pidRegex = "\\d{9}".r

  private val requiredFields = Map[String, String => Boolean](
    "byr" -> (y => birthRange.contains(y.toInt)),
    "iyr" -> (y => issueRange.contains(y.toInt)),
    "eyr" -> (y => expireRange.contains(y.toInt)),
    "hgt" -> (height => {
      val n = height.takeWhile(_.isDigit).toInt
      height.takeRight(2) match {
        case "in" => hgtInRange.contains(n)
        case "cm" => hgtCmRange.contains(n)
        case _ => false
      }
    }),
    "hcl" -> hairRegex.matches,
    "ecl" -> eclOptions.contains,
    "pid" -> pidRegex.matches,
  )

  override def day: Int = 4

  override def parsePuzzleInput(input: List[String]): List[Map[String, String]] =
    input
      .mkString("\n")
      .split("\\n\\n")
      .map(passportString => parsingRegex.findAllMatchIn(passportString).toList)
      .map(_.map(regexMatch => (regexMatch.group(1), regexMatch.group(2))).toMap)
      .toList

  override def runA(in: List[Map[String, String]]): Int = {
    in.count(passport => requiredFields.keys.forall(passport.contains))
  }

  override def runB(in: List[Map[String, String]]): Int = {
    in.count(passport => requiredFields.forall(prop => passport.contains(prop._1) && prop._2(passport(prop._1))))
  }
}
