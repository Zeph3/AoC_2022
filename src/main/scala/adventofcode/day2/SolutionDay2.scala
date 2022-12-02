package adventofcode.day2

import adventofcode.day2.SolutionDay2Data.{DRAW, LOSS, Paper, Rock, Scissor, Strat, WIN}
import util.FileUtil.readFileAndSplitBy

object SolutionDay2 extends App {

  val fileInputDelimited = readFileAndSplitBy("src/main/source/day2/rock_paper_scissor", "\n")

  val transformed: List[(Strat, Strat)] = fileInputDelimited.toList.map(line => toStrategyTuple(line.split(" ")))
  val transformedV2: List[(Strat, Strat)] = fileInputDelimited.toList.map(line => toStrategyTupleV2(line.split(" ")))

  val result = transformed.map(determinePoints)
  val resultV2 = transformedV2.map(determinePoints)

  println("If all goes well: " + result.sum)
  println("If all goes well V2: " + resultV2.sum)

  def determinePoints(matchUp: (Strat, Strat)): Int = {
    val matchResult = matchUp match {
      case (Rock, Paper) => WIN
      case (Rock, Scissor) => LOSS
      case (Paper, Scissor) => WIN
      case (Paper, Rock) => LOSS
      case (Scissor, Rock) => WIN
      case (Scissor, Paper) => LOSS

      case (_, _) => DRAW // other scenarios
    }
    matchResult + matchUp._2.value
  }

  def toStrategyTuple(matchup: Array[String]): (Strat, Strat) = {
    matchup match {
      case Array(x, y) => (toStrategy(x), toStrategy(y))
    }
  }

  def toStrategy(strategy: String): Strat = {
    strategy match {
      case "A" | "X" => Rock
      case "B" | "Y" => Paper
      case "C" | "Z" => Scissor
    }
  }

  def toStrategyTupleV2(matchup: Array[String]): (Strat, Strat) = {
    val opponent: Strat = toStrategy(matchup.head)
    val strategy: Strat = toStrategyV2(opponent, matchup.tail.head)

    (opponent, strategy)
  }

  def toStrategyV2(opponent: Strat, strategy: String): Strat = {
    (opponent, strategy) match {
      case (Rock, "X") => Scissor
      case (Rock, "Y") => Rock
      case (Rock, "Z") => Paper

      case (Paper, "X") => Rock
      case (Paper, "Y") => Paper
      case (Paper, "Z") => Scissor

      case (Scissor, "X") => Paper
      case (Scissor, "Y") => Scissor
      case (Scissor, "Z") => Rock
    }
  }
}

object SolutionDay2Data {
  val WIN: Int = 6
  val DRAW: Int = 3
  val LOSS: Int = 0

  trait Strat {
    def value: Int
  }

  case object Rock extends Strat {
    override def value: Int = 1
  }

  case object Paper extends Strat {
    override def value: Int = 2
  }

  case object Scissor extends Strat {
    override def value: Int = 3
  }
}
