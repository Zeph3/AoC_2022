package adventofcode

import util.FileUtil._

import scala.util.{Success, Try}

object SolutionDay7 extends App {

  val fileInputRaw: List[String] = readFileAndSplitBy("src/main/source/day7/directories", "\n").toList

  val sizes = build(fileInputRaw)
  val part1 = sizes.filter(_ < 100000).sum

  val needed = 30000000 - (70000000 - sizes.max)
  val minimumToBeDeleted = sizes.filter(_ >= needed).min

  println("part 1: " + part1)
  println("part 2: " + minimumToBeDeleted)


  def build(input: Seq[String]): Seq[Int] = {
    val root = List.empty[String]
    val initial = (root, Map(root -> 0))
    val (_, pathAndSizeMap) = input.foldLeft(initial) { case ((path, pathAndSizeMap), line) =>
      line match {
        case "$ cd .." => (path.tail, pathAndSizeMap)
        case s"$$ cd $name" => (name :: path, pathAndSizeMap.updated(name :: path, 0))
        case s"$size $name" if size.head.isDigit =>
          (path, path.tails.foldLeft(pathAndSizeMap)((acc, next) => acc.updated(next, pathAndSizeMap(next) + size.toInt)))
        case _ => (path, pathAndSizeMap)
      }
    }
    pathAndSizeMap.values.toSeq
  }

}
