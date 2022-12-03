package adventofcode.day3

import adventofcode.day3.SolutionDay3Data.Rucksack
import util.FileUtil.readFileAndSplitBy

object SolutionDay3 extends App {

  val fileInputDelimited: Array[String] = readFileAndSplitBy("src/main/source/day3/rucksack_content", "\n")

  val transformedPart1: List[Int] = fileInputDelimited.toList
    .map(toRucksack)
    .map(toRucksackContentDiff)
    .map(toPriorityValue)

  val transformedPart2: List[Int] = fileInputDelimited.grouped(3)
    .map(_.reduce(_.intersect(_))) // reduce instead of fold, because fold required id which will be intersected.
    .map(_.charAt(0))
    .map(toPriorityValue)
    .toList

  println("Sum of priority diff: " + transformedPart1.sum)

  println("Sum of priority diff within team: " + transformedPart2.sum)


  def toRucksack(items: String): Rucksack = {
    val halfPoint = items.length / 2
    val splitItems = items.splitAt(halfPoint)
    Rucksack(splitItems._1, splitItems._2)
  }

  def toRucksackContentDiff(rucksack: Rucksack): Char = {
    rucksack.firstCompartment
      .toCharArray
      .filter(rucksack.secondCompartment.contains(_))
      .head //sometimes duplicates appear twice, only keep 1
  }

  def toPriorityValue(diff: Char): Int = {
    val ascii = diff.toInt
    if (ascii > 96 && ascii <= 122)
      ascii - 96
    else (ascii - 65) + 27
  }

}

object SolutionDay3Data {


  case class Rucksack(firstCompartment: String, secondCompartment: String)

}
