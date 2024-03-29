package scraper

import models.Fixture
import org.jsoup._
import org.jsoup.nodes.Document

import scala.jdk.CollectionConverters._

object Scraper {

  val prefix: String = "https://ciubyker.leaguerepublic.com/matches/77396460/1_641815645/-1/-1/-1/"
  val suffix: String = ".html"

  def document(index: Int): Document = {
    val doc = Jsoup.connect(s"$prefix$index$suffix").get()
    doc
  }

  def getTotalFixturePages: Int = {
    val doc = document(1)
    val pageLink = doc.select("ul.pagination > li:nth-last-child(2) > a")
    pageLink.text().toInt
  }

  def getFixturesForPage(index: Int, filterTeam: Option[String] = None): List[Fixture] = {
    val doc = document(index)
    val fixtures = doc.select("div.table-scroll > table > tbody > tr")
    val newFixtures = for (fixture <- fixtures.asScala) yield {
      val date = fixture.select("td:nth-child(2)").text().replace(" 20:00", "")
      val home = fixture.select("td:nth-child(3) > a").text()
      val away = fixture.select("td:nth-child(5) > a").text()
      Fixture(date, home, away)
    }
    newFixtures.filter(fixture => filterTeam.forall(List(fixture.homeTeam, fixture.awayTeam).contains(_))).toList
  }

}