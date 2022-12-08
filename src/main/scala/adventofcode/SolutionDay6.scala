package adventofcode

import util.FileUtil._

import scala.util.{Success, Try}

object SolutionDay6 extends App {

  val packageMarker = 4
  val messageMarker = 14

  val fileInputRaw: String = readFileAndGetAsString("src/main/source/day6/signal_system_input")
  val inputAsIndexedCharArray = fileInputRaw.toCharArray.zipWithIndex

  val transformed = inputAsIndexedCharArray
    .map(indexAndChar => scanForUniqueCharsInStringForIndex(indexAndChar._2, fileInputRaw, packageMarker))
    .filter(_._2.size == packageMarker)

  val transformedV2 = inputAsIndexedCharArray
    .map(indexAndChar => scanForUniqueCharsInStringForIndex(indexAndChar._2, fileInputRaw, messageMarker))
    .filter(_._2.size == messageMarker)

  println(transformed.toList.head._1)
  println(transformedV2.toList.head._1)

  def scanForUniqueCharsInStringForIndex(index: Int, rawString: String, packetMarker: Int): (Int, Set[Char]) = {
    Try {
      rawString.substring(index, index + packetMarker)
    } match {
      case Success(str) => (index + packetMarker, str.toSet)
      case _ => (index, Set.empty)
    }
  }
}
