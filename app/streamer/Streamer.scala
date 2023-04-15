package streamer

import akka.stream.scaladsl.Source
import scraper.Scraper

trait Streamer {

  def wikiSource: Source[String, _] = {
    val document = Scraper.document
    val iterable = (1 to 10).map(_ => document)
    val vlah = Source(iterable)
    vlah.takeWhile(d => d.hasText).map(_.title())
  }

}
