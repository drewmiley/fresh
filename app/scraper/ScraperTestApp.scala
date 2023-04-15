package scraper

import scala.jdk.CollectionConverters._

object ScraperTestApp {

  def main(args: Array[String]): Unit = {
    val doc = Scraper.document
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
}