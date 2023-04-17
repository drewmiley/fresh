package scraper

import models.Fixture
import org.jsoup._
import org.jsoup.nodes.Document

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.jdk.CollectionConverters._

object Scraper {

  val prefix: String = "https://ciubyker.leaguerepublic.com/matches/77396460/1_641815645/-1/-1/-1/"
  val suffix: String = ".html"

  def documentFuture(index: Int): Future[Document] = Future {
    val doc = Jsoup.connect(s"$prefix$index$suffix").get()
    doc
  }

  def getTotalFixturePagesFuture: Future[Int] = {
    val docFuture = documentFuture(1)
    docFuture map { doc =>
      val pageLink = doc.select("ul.pagination > li:nth-last-child(2) > a")
      pageLink.text().toInt
    }
  }

  def getFixturesForPageFuture(index: Int, filterTeam: Option[String] = None): Future[List[Fixture]] = {
    val docFuture = documentFuture(index)
    docFuture map { doc =>
      val fixtures = doc.select("div.table-scroll > table > tbody > tr")
      val newFixtures = for (fixture <- fixtures.asScala) yield {
        val date = fixture.select("td:nth-child(2)").text().replace(" 20:00", "")
        val home = fixture.select("td:nth-child(3) > a").text()
        val away = fixture.select("td:nth-child(5) > a").text()
        Fixture(date, home, away)
      }
      newFixtures
        .filter(fixture => filterTeam.forall(List(fixture.homeTeam, fixture.awayTeam).contains(_)))
        .toList
    }
  }

  def getFixturesForPageFutureAsHtml(index: Int, filterTeam: Option[String] = None): Future[String] =
    getFixturesForPageFuture(index, filterTeam) map { fixturesForPage => s"<div id='${index}'>${fixturesForPage.mkString("<p/>")}</div>"}

}