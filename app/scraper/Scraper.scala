package scraper

import org.jsoup._
import org.jsoup.nodes.Document

object Scraper {

  val prefix: String = "https://ciubyker.leaguerepublic.com/matches/77396460/1_641815645/-1/-1/-1/"
  val suffix: String = ".html"

  def document(index: Int): Document = {
    val doc = Jsoup.connect(s"$prefix$index$suffix").get()
    doc
  }

}