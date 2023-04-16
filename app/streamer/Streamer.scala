package streamer

import akka.stream.scaladsl.Source
import scraper.Scraper

trait Streamer {

  def streamingSource(filterTeam: Option[String] = None): Source[String, _] = {
    val totalFixturePages = Scraper.getTotalFixturePages
//    TODO: Source should load one at a time, rather than all at once
    val iterable = (1 to totalFixturePages).flatMap(index => Scraper.getFixturesForPage(index, filterTeam))
    val source = Source(iterable)
    source.map(_.toString)
  }

}
