package streamer

import akka.stream.scaladsl.Source
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import scala.concurrent.duration._

trait Streamer {

  def stringSource: Source[String, _] = {
    val df: DateTimeFormatter = DateTimeFormatter.ofPattern("HH mm ss")
    val tickSource = Source.tick(0.millis, 100.millis, "TICK")
    val s = tickSource.map(_ => df.format(ZonedDateTime.now()))
    s
  }

  def wikiSource: Source[String, _] = {
    val df: DateTimeFormatter = DateTimeFormatter.ofPattern("HH mm ss")
    val tickSource = Source.tick(0.millis, 100.millis, "TICK")
    val s = tickSource.map(_ => df.format(ZonedDateTime.now()))
    s
//    GET IN WIKI TITLE HERE
  }

  def document: Document = {
    val doc = Jsoup.connect("http://en.wikipedia.org/").get()
    doc
  }

}
