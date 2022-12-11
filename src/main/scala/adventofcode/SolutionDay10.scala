package adventofcode

import adventofcode.Day9Data.Point
import util.FileUtil._

import scala.annotation.tailrec

object SolutionDay10 extends App {

  val fileInputRaw: List[String] = readFileAndSplitBy("src/main/source/day10/example", "\n").toList

  // adx delay of 2
  // noop delay of 1
  // parse raw input with index so that

  def prep(input: List[String]): List[Int] = {
    input.flatMap { line =>
      line.strip() match {
        case s"$addx $n" => List.fill(1)(0) :+ n.toInt
        case s"$noop" => List.fill(1)(0)
      }
    }
  }
    def processInstructions(input: List[String], cycles: Int): Int = {
      val parsedInstructions = prep(input)

      parsedInstructions.take(cycles - 1).foldLeft(1)(_ + _) * cycles
    }

  def part1(signals: List[Int]): Unit = {
    println(signals.map(cycles => processInstructions(fileInputRaw, cycles)).sum)
  }

  part1(List(20, 60, 100, 140, 180, 220))
}
