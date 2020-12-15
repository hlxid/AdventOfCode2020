package com.github.daniel0611.aoc2020

import scala.annotation.tailrec

case class Console(instructions: List[CpuInstruction], state: CpuState = CpuState(), executedInstructions: Set[Int] = Set()) {
  @tailrec
  final def run(): Console = {
    if (isLooping || isDone) return this
    val newState = step(state)
    copy(state = newState, executedInstructions = executedInstructions + state.pc).run()
  }

  private def step(state: CpuState): CpuState = instructions(state.pc).execute(state)

  def isLooping: Boolean = executedInstructions.contains(state.pc)

  def isDone: Boolean = state.pc == instructions.length
}

case class CpuState(pc: Int = 0, accumulator: Int = 0)

abstract sealed case class CpuInstruction(param: Int) {
  def execute(state: CpuState): CpuState
}

object CpuInstruction {

  class NopInstruction(param: Int) extends CpuInstruction(param) {
    override def execute(state: CpuState): CpuState = state.copy(pc = state.pc + 1)
  }

  class AccInstruction(param: Int) extends CpuInstruction(param) {
    override def execute(state: CpuState): CpuState = state.copy(pc = state.pc + 1, accumulator = state.accumulator + param)
  }

  class JmpInstruction(param: Int) extends CpuInstruction(param) {
    override def execute(state: CpuState): CpuState = state.copy(pc = state.pc + param)
  }

}

import com.github.daniel0611.aoc2020.CpuInstruction._

object Day8 extends AoCChallenge[List[CpuInstruction], Int] {
  override def day: Int = 8

  override def parsePuzzleInput(input: List[String]): List[CpuInstruction] =
    input.map(rawInstr => {
      val parts = rawInstr.split(" ")
      (parts.head, parts(1).toInt) match {
        case ("nop", p) => new NopInstruction(p)
        case ("acc", p) => new AccInstruction(p)
        case ("jmp", p) => new JmpInstruction(p)
        case i => throw new Exception(s"unknown instruction: $i")
      }
    })

  override def runA(instr: List[CpuInstruction]): Int = Console(instr).run().state.accumulator

  override def runB(instr: List[CpuInstruction]): Int =
    instr.zipWithIndex
      .filter {
        case (_: AccInstruction, _) => false
        case _ => true
      }
      .map(a => {
        val reversedInstr = a._1 match {
          case i: NopInstruction => new JmpInstruction(i.param)
          case i: JmpInstruction => new NopInstruction(i.param)
          case _ => throw new Exception("invalid instruction")
        }
        instr.updated(a._2, reversedInstr)
      })
      .map(instructions => Console(instructions).run())
      .find(_.isDone).map(_.state.accumulator).get
}
