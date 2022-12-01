package day1

import scala.io.Source

object SolutionDay1 extends App {

  println(System.getProperty("user.dir"))

  val source = Source.fromFile("src/main/source/day1/calories_list")

  val stringResult: Array[Array[Int]] = source.mkString.split("\n\n").map(inner => inner.split("\n").map(inner => inner.toInt))

  val intresult = stringResult.map(inner => inner.sum)

  source.close()

  println(intresult.max)

  println(intresult.sorted.reverse.take(3).sum)

}
