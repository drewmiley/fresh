package scraperexample

import net.ruippeixotog.scalascraper.browser._
import net.ruippeixotog.scalascraper.dsl.DSL.Extract._
import net.ruippeixotog.scalascraper.dsl.DSL._

object ScalaScraper {

  def main(args: Array[String]): Unit = {
    val browser = JsoupBrowser()
    val doc = browser.get("http://en.wikipedia.org/")
    val title = doc.title
    println(title)
    val inTheNews = doc >> elementList("#mp-itn b a")
    println(inTheNews)
    val onThisDay = doc >> elementList("#mp-otd b a")
    println(onThisDay)
    val didYouKnow = doc >> elementList("#mp-dyk b a")
    println(didYouKnow)
    val otds = for (otd <- onThisDay) yield (otd >> attr("title"), otd >> attr("href"))
    println(otds)
    val headers = for (otd <- onThisDay) yield otd >> text
    println(headers)
  }
}