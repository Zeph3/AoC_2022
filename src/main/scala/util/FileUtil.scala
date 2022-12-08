package util

import scala.io.Source
import scala.util.Using

object FileUtil {

  def readFileAndSplitBy(path: String, regex: String): Array[String] = {

    Using(Source.fromFile(path)) {
      _.mkString
        .split(regex)
    }.get
  }

  def readFileAndGetAsString(path: String): String = {

    Using(Source.fromFile(path))(_.mkString).get
  }

}
