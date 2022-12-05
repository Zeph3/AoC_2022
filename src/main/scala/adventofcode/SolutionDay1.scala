package adventofcode

import util.FileUtil.readFileAndSplitBy

object SolutionDay1 extends App {

  val fileInputDelimited = readFileAndSplitBy("src/main/source/day1/calories_list", "\n\n")

  val fileTransformedToArrOfIntSum = fileInputDelimited
    .map(inner => inner.split("\n")
      .map(inner => inner.toInt).sum
    )

  println(fileTransformedToArrOfIntSum.max)

  println(fileTransformedToArrOfIntSum.sorted.reverse.take(3).sum)

}
