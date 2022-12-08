package adventofcode

import util.FileUtil._

import scala.collection.immutable.IndexedSeq
import scala.util.Try

object SolutionDay8 extends App {

  val fileInputRaw: Array[String] = readFileAndSplitBy("src/main/source/day8/forest_data", "\n")

  val inputAsIndexed: IndexedSeq[IndexedSeq[Int]] = fileInputRaw.toIndexedSeq.map(_.toIndexedSeq.map(_.asDigit))

  val transformedInputForTraversing: List[((Int, Int), Int)] = for {
    (outerVal, outerIndex) <- fileInputRaw.toList.zipWithIndex
    (innerVal, innerIndex) <- outerVal.toList.zipWithIndex
  } yield (outerIndex, innerIndex) -> innerVal.asDigit

  val resultPart1 = transformedInputForTraversing.filter(idxV => isEdge(idxV) || check(idxV))
  val resultPart2 = transformedInputForTraversing.map(calculateScenicScore)

  println(resultPart1.size)
  println(resultPart2.max)


  def isEdge(indexAndValue: ((Int, Int), Int)): Boolean =
    indexAndValue._1._1 == 0 || indexAndValue._1._1 == inputAsIndexed.size -1 ||
      indexAndValue._1._2 == 0 || indexAndValue._1._2 == inputAsIndexed(0).size -1

  def check(indexAndValue: ((Int, Int), Int)): Boolean = {
    val (north, south) = takeHorizontal(indexAndValue)
    val (east, west) = takeVertical(indexAndValue)

    val northF = north.filter(input => input >= indexAndValue._2)
    val southF = south.filter(input => input >= indexAndValue._2)
    val eastF = east.filter(input => input >= indexAndValue._2)
    val westF = west.filter(input => input >= indexAndValue._2)

    northF.isEmpty || southF.isEmpty || eastF.isEmpty || westF.isEmpty
  }

  def calculateScenicScore(indexAndValue: ((Int, Int), Int)): Int = {

    val currTreeSize = indexAndValue._2
    val (up, down) = takeHorizontal(indexAndValue)
    val (left, right) = takeVertical(indexAndValue)

    takeUntil(up, currTreeSize).size *
      takeUntil(down, currTreeSize).size *
      takeUntil(left, currTreeSize).size *
      takeUntil(right, currTreeSize).size
  }

  def takeUntil(trees: List[Int], sizeOfCurrentTree: Int): List[Int] = {
    trees.span(_ < sizeOfCurrentTree) match {
      case (h, t) => if (t.nonEmpty) h ::: t.take(1) else h
    }
  }

  def takeHorizontal(indexAndValue: ((Int, Int), Int)): (List[Int], List[Int]) = {
    (transformedInputForTraversing.filter(input => indexAndValue._1._1 > input._1._1 && indexAndValue._1._2 == input._1._2).map(_._2).reverse,
      transformedInputForTraversing.filter(input => indexAndValue._1._1 < input._1._1 && indexAndValue._1._2 == input._1._2).map(_._2))
  }

  def takeVertical(indexAndValue: ((Int, Int), Int)): (List[Int], List[Int]) = {
    (transformedInputForTraversing.filter(input => indexAndValue._1._2 > input._1._2 && indexAndValue._1._1 == input._1._1).map(_._2).reverse,
      transformedInputForTraversing.filter(input => indexAndValue._1._2 < input._1._2 && indexAndValue._1._1 == input._1._1).map(_._2))
  }
}
