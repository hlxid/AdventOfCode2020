package com.github.daniel0611.aoc2020

case class Ticket(fields: List[Int])

case class TicketInfo(fieldInfo: Map[String, List[Range]], mine: Ticket, nearby: List[Ticket]) {
  def isInFieldInfo(num: Int): Boolean = fieldInfo.values.exists(_.exists(_.contains(num)))
}

object Day16 extends AoCChallenge[TicketInfo, Long] {
  private val infoRegex = "([a-z ]+): (\\d+)-(\\d+) or (\\d+)-(\\d+)".r

  override def day: Int = 16

  override def parsePuzzleInput(input: List[String]): TicketInfo = {
    val parts = input.mkString("\n").split("\\n\\n").map(_.split("\\n").toList)
    TicketInfo(parseFieldInfo(parts.head), parseTicket(parts(1)(1)), parts(2).drop(1).map(parseTicket))
  }

  private def parseFieldInfo(in: List[String]) = in.map {
    case infoRegex(key, l1, h1, l2, h2) => key -> List(Range.inclusive(l1.toInt, h1.toInt), Range.inclusive(l2.toInt, h2.toInt))
    case x =>
      println(x)
      null
  }.toMap

  private def parseTicket(in: String) = Ticket(in.split(",").map(_.toInt).toList)

  override def runA(input: TicketInfo): Long = input.nearby
    .flatMap(_.fields)
    .filter(num => {
      !input.isInFieldInfo(num)
    })
    .sum

  override def runB(input: TicketInfo): Long = {
    val validTickets = input.nearby.filter(ticket => {
      ticket.fields.forall(input.isInFieldInfo)
    })

    val indices = input.fieldInfo.flatMap(info => {
      val i = input.mine.fields.indices.find(i => validTickets.forall(ticket => info._2.exists(_.contains(ticket.fields(i))))).get
      if(info._1.startsWith("departure ")) Some(i) else None
    })

    indices.map(i => input.mine.fields(i)).product
  }
}
