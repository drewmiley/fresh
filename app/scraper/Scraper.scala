package scraper

import org.jsoup._
import org.jsoup.nodes.Document

object Scraper {

  def document: Document = {
    val doc = Jsoup.connect("http://en.wikipedia.org/").get()
    doc
  }
}