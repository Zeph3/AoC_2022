package util

import scala.io.Source

object FileUtil {

  def readFileAndSplitBy(path: String, regex: String): Array[String] = {
    val source = Source.fromFile(path)
    val splitResult = source.mkString.split(regex)
    source.close()

    splitResult
  }

}
