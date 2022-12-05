package adventofcode

import util.FileUtil.readFileAndSplitBy

object SolutionDay5 extends App {

  val fileInputDelimited: Array[String] = readFileAndSplitBy("src/main/source/day5/storage_and_procedure", "\n\n")
  val cargoStorage: Array[String] = fileInputDelimited(0).split("\n")
  val commands: Array[String] = fileInputDelimited(1).split("\n")



  println(commands.toList)
  println(cargoStorage.toList)

}
