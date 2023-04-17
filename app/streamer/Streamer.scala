package streamer

import akka.stream.scaladsl.{Merge, Source}
import scraper.Scraper

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

trait Streamer {

  def streamingSourceFuture(filterTeam: Option[String] = None): Future[Source[String, _]] = {
    val totalFixturePagesFuture = Scraper.getTotalFixturePagesFuture
    totalFixturePagesFuture map { totalFixturePages =>
      val listFutureString = (1 to totalFixturePages).map(index => Scraper.getFixturesForPageFuture(index, filterTeam)).toList
      val listOfSources: List[Source[String, _]] = listFutureString.map(Source.future)
      val source: Source[String, _] = listOfSources.reduce((a, b) => Source.combine(a, b)(Merge(_)))
      source
    }
  }

}
