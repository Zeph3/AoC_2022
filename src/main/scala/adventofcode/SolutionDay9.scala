package adventofcode

import adventofcode.Day9Data.Point
import util.FileUtil._

object SolutionDay9 extends App {

  val fileInputRaw: Array[String] = readFileAndSplitBy("src/main/source/day9/input", "\n")

  val parsedInput = parse(fileInputRaw)

  def part1(): Unit = {
    println(applyDirections(parsedInput, 2))
  }

  def part2(): Unit = {
    println(applyDirections(parsedInput, 10))
  }

  part1()
  part2()

//  def part1(input: Seq[Point]): Unit = {
//    applyDirections(input, 2)
//  }

  def parse(input: Seq[String]): Seq[Point] = {
    val directionToPoint: Map[String, Point] = Map("R" -> Point(0,1), "L" -> Point(0,-1),
                                            "U" -> Point(1,0), "D" -> Point(-1,0))
    input.flatMap { line =>
      val s"$direction $n" = line
      List.fill(n.toInt)(directionToPoint(direction))
    }
  }

  def applyDirections(directions: Seq[Point], size: Int): Int = {
    val initialState = List.fill(size)(Point(0,0))
    directions.scanLeft(initialState) { case (rope, currentDirection) =>
      val next = rope.head + currentDirection
      rope.tail.scanLeft(next) { (previous, current) =>
        if (current.touching(previous)) current else current + previous.delta(current)
      }
    }.map(_.last).distinct.size
  }
}

object Day9Data{
  case class Point(x: Int, y: Int) {
    def +(other: Point): Point = Point(x + other.x, y + other.y)

    def delta(other: Point): Point = Point((x - other.x).sign, (y - other.y).sign)

    def touching(other: Point): Boolean = (x - other.x).abs <= 1 && (y - other.y).abs <= 1
  }
}
