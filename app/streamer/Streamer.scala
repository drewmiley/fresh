package streamer

import akka.stream.scaladsl.{Merge, Source}
import scraper.Scraper

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

trait Streamer {

  def streamingSourceFuture(filterTeam: Option[String] = None): Future[Source[String, _]] = {
    Scraper.getTotalFixturePagesFuture map { totalFixturePages =>
      (1 to totalFixturePages)
        .map(index => Scraper.getFixturesForPageFutureAsHtml(index, filterTeam))
        .map(Source.future)
        .reduce((acc, d) => Source.combine(acc, d)(Merge(_)))
    }
  }

}
