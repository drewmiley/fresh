package scraper

import org.jsoup._
import org.jsoup.nodes.Document

import scala.jdk.CollectionConverters._

object Scraper {

  def main(args: Array[String]): Unit = {
    val doc = Jsoup.connect("http://en.wikipedia.org/").get()
    val title = doc.title()
    println(title)
    val inTheNews = doc.select("#mp-itn b a")
    println(inTheNews)
    val onThisDay = doc.select("#mp-otd b a")
    println(onThisDay)
    val didYouKnow = doc.select("#mp-dyk b a")
    println(didYouKnow)
    val otds = for(otd <- onThisDay.asScala) yield (otd.attr("title"), otd.attr("href"))
    println(otds)
    val headers = for (otd <- onThisDay.asScala) yield otd.text
    println(headers)
  }

  def document: Document = {
    val doc = Jsoup.connect("http://en.wikipedia.org/").get()
    doc
  }
}