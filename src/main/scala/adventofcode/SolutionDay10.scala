package adventofcode

import adventofcode.Day9Data.Point
import util.FileUtil._

object SolutionDay10 extends App {

  val fileInputRaw: List[String] = readFileAndSplitBy("src/main/source/day10/example", "\n").toList

  // adx delay of 2
  // noop delay of 1
  // parse raw input with index so that

  def prep(input: List[String]): List[List[Int]] = {
    input.map { line => line match P



      if (line.contains("noop"))
        List.fill(2)(0) else 0 :: 0 :: List(line.split(" ").tail.head.toInt)
    }
  }

println(fileInputRaw.toList)

}
