package com.github.daniel0611.aoc2020

case class CustomsForm(group: Array[Array[Char]]) {
  private def getQuestions: Array[Char] = group.flatten.distinct

  def getCountAgreedByAnyone: Int = getQuestions.length

  def getCountAgreedByEveryone: Int = getQuestions.count(question => group.forall(person => person.contains(question)))
}

object Day6 extends AoCChallenge[Array[CustomsForm], Int] {
  override def day: Int = 6

  override def parsePuzzleInput(input: List[String]): Array[CustomsForm] =
    input.mkString("\n").split("\\n\\n")
      .map(_.split("\\n").map(_.toCharArray))
      .map(CustomsForm)

  override def runA(input: Array[CustomsForm]): Int = input.map(_.getCountAgreedByAnyone).sum

  override def runB(input: Array[CustomsForm]): Int = input.map(_.getCountAgreedByEveryone).sum
}
