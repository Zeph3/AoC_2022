package adventofcode.day4

import util.FileUtil.readFileAndSplitBy

object SolutionDay4 extends App {

  val fileInputDelimited: Array[String] = readFileAndSplitBy("src/main/source/day4/cleanup_sections", "\n")

  val resultPart1 = fileInputDelimited
    .map(fromStringTupleOfSets)
    .map(determineCompleteOverlap)
    .sum

  val resultPart2 = fileInputDelimited
    .map(fromStringTupleOfSets)
    .map(determinePartialOverlap)
    .sum

  println("The amount of pair that completely overlap: " + resultPart1)
  println("The amount of pair that partially overlap: " + resultPart2)

  def fromStringTupleOfSets(line: String): (Set[Int], Set[Int]) = {
    line.split(",")
      .map(_.split("-")) match {
      case Array(Array(firstBeginning, firstEnd), Array(secondStart, secondEnd)) =>
        ((firstBeginning.toInt to firstEnd.toInt).toSet, (secondStart.toInt to secondEnd.toInt).toSet)
    }
  }

  def determineCompleteOverlap(ranges: (Set[Int], Set[Int])): Int = {
    (ranges._1.subsetOf(ranges._2) || ranges._2.subsetOf(ranges._1)).toInt
  }

  def determinePartialOverlap(ranges: (Set[Int], Set[Int])): Int = {
    ranges._1.intersect(ranges._2).nonEmpty.toInt
  }

  implicit class BooleanOps(bool: Boolean) {
    def toInt: Int = if (bool) 1 else 0
  }
}
