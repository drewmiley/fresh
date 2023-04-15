package streamer

import akka.stream.scaladsl.Source
import scraper.Scraper

trait Streamer {

  def streamingSource: Source[String, _] = {
    val iterable = (1 to 10).map(index => Scraper.document(index))
    val source = Source(iterable)
    source.takeWhile(d => d.hasText).map(_.title())
  }

}
