package streamer

import akka.stream.scaladsl.Source
import models.Fixture
import scraper.Scraper

trait Streamer {

  val filterTeam = "BLUE BELL A"

  def streamingSource: Source[Fixture, _] = {
    val totalFixturePages = Scraper.getTotalFixturePages
    val iterable = (1 to totalFixturePages).flatMap(index => Scraper.getFixturesForPage(index, Option(filterTeam)))
    val source = Source(iterable)
    source
  }

}
