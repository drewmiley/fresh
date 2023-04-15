package scraper

import models.Fixture
import org.jsoup._
import org.jsoup.nodes.Document

object Scraper {

  val prefix: String = "https://ciubyker.leaguerepublic.com/matches/77396460/1_641815645/-1/-1/-1/"
  val suffix: String = ".html"

  def document(index: Int): Document = {
    val doc = Jsoup.connect(s"$prefix$index$suffix").get()
    doc
  }

  def getTotalFixturePages: Int = {
    val doc = document(1)
    val pageLink = doc.select(".pagination > li:nth-last-child(2) > a")
    pageLink.text().toInt
  }

  def getFixturesForPage(index: Int, filterTeam: Option[String] = None): List[Fixture] = {
    val doc = document(index)
    List()
  }

}