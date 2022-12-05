package adventofcode

import util.FileUtil.readFileAndSplitBy

import scala.collection.mutable

object SolutionDay5 extends App {

  val fileInputDelimited: Array[String] = readFileAndSplitBy("src/main/source/day5/storage_and_procedure", "\n\n")
  val cargoStorage: Array[String] = fileInputDelimited(0).split("\n")
  val commands: Array[String] = fileInputDelimited(1).split("\n")

  val cargoStorageTransformed = transformCargoStorage(cargoStorage)
  val cargoStorageTransformedPart2 = transformCargoStorage(cargoStorage)
  val commandsTransformed = transformCommands(commands)

  applyCommandsV9000(commandsTransformed, cargoStorageTransformed)
  applyCommandsV9001(commandsTransformed, cargoStorageTransformedPart2)

  println("#"*10 + " Part 1 " + "#"*10)

  cargoStorageTransformed.foreach(stack => {
    print(stack.takeRight(1)(0))
  })

  println("")
  println("#"*10 + " Part 2 " + "#"*10)

  cargoStorageTransformedPart2.foreach(stack => {
    print(stack.takeRight(1)(0))
  })

  def applyCommandsV9000 (commands: Array[(Int, Int, Int)], cargoStore: Array[mutable.ArrayDeque[Char]]): Unit = {
    commands.foreach { command =>

      for (_ <- 1 to command._1) {
        val removed = cargoStore(command._2).removeLast()
        cargoStore(command._3).append(removed)
      }
    }
  }


  def applyCommandsV9001(commands: Array[(Int, Int, Int)], cargoStore: Array[mutable.ArrayDeque[Char]]): Unit = {
    commands.foreach { command =>
      val taken = cargoStore(command._2).takeRight(command._1)
      cargoStore(command._3).appendAll(taken)

      for (_ <- 1 to command._1) {
        cargoStore(command._2).removeLast()
      }
    }
  }

  def transformCargoStorage(rawLines: Array[String]): Array[mutable.ArrayDeque[Char]] = {
    val cargoStorage: Array[mutable.ArrayDeque[Char]] = Array.fill(9)(new mutable.ArrayDeque[Char]())

    rawLines.dropRight(1)
      .foreach { line =>
        for (index <- 0 to 8) {
          val formattedLine = String.format("%-35s", line)
          val content = findCargoContent(formattedLine, index)
          if(content.isLetter) {
            cargoStorage(index).prepend(content)
          }
        }
      }
    cargoStorage
  }

  def transformCommands(commands: Array[String]): Array[(Int, Int, Int)] = {
    commands.map { line =>
      val splitLine = line.split(" ")
      val amount = splitLine(1).toInt
      val from = splitLine(3).toInt
      val to = splitLine(5).toInt
      (amount, from-1, to-1)
    }
  }

  def findCargoContent(line: String, cargoN: Int): Char = {
    val start = cargoN*4
    val end = cargoN*4 + 3
    line.substring(start, end).charAt(1)
  }
}
